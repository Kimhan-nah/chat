- [0. Chat-Server](#0-chat-server)
- [1. 실행 방법](#1-실행-방법)
    - [1-1. MySQL 데이터베이스 생성 및 테이블 초기화](#1-1-mysql-데이터베이스-생성-및-테이블-초기화)
    - [1-2. application.yml 파일 추가](#1-2-applicationyml-파일-추가)
    - [1-3. IntelliJ IDEA에서 프로젝트 실행](#1-3-intellij-idea에서-프로젝트-실행)
- [2. DB ERD](#2-db-erd)
- [3. Diagram](#3-diagram)

# 0. Chat-Server

| 실시간 채팅 서버 구현

# 1. 실행 방법

| MySQL, IntelliJ IDEA가 설치되어 있어야 합니다.

### 1-1. MySQL 데이터베이스 생성 및 테이블 초기화

1. MySQL에 접속
   ```
   mysql -u [유저네임] -p [비밀번호]
   ```

2. 데이터베이스 생성
   ```sql
   CREATE DATABASE chat_db;
   ```

3. MySQL 종료
   ```
   exit;
   ```

4. 스키마 파일로 테이블 초기화
   ```
   mysql -u [유저네임] -p [비밀번호] chat_db < ./src/main/resources/schema.sql
   ```

### 1-2. application.yml 파일 추가

`src/main/resources/application.yml` 파일을 생성하고 다음 내용을 추가해주세요.

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/chat_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: [ 유저네임 ]
    password: [ 비밀번호 ]
  jpa:
    database-platform: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: false
```

**주의**: `[유저네임]`과 `[비밀번호]` 부분을 실제 MySQL 사용자 이름과 비밀번호로 수정해주세요.

### 1-3. IntelliJ IDEA에서 프로젝트 실행

- IntelliJ IDEA를 실행하고 프로젝트를 엽니다.
- Gradle 도구 창을 엽니다 (보통 오른쪽 사이드바에 위치).
- 프로젝트 빌드
    - Gradle 도구 창에서 Tasks > build > build를 더블 클릭하여 프로젝트를 빌드합니다.
    - 또는 터미널에서 다음 명령을 실행합니다.
      ```
      ./gradlew build
      ```
- 애플리케이션 실행
    - src/main/java 디렉토리에서 메인 애플리케이션 클래스 (보통 ChatApplication.java)를 찾습니다.
    - 이 파일을 우클릭하고 "Run 'ChatApplication'"을 선택합니다.
    - 또는 Gradle 도구 창에서 Tasks > application > bootRun을 더블 클릭하여 실행합니다.

# 2. DB ERD

![ERD](./img/chat-server-db.png)

# 3. Diagram

```mermaid
sequenceDiagram
participant U as 유저
participant C as 클라이언트
participant API as REST API 서버
participant WS as WebSocket 서버
participant DB as 데이터베이스

    Note over U,DB: 1. 채팅방 목록 조회
    U->>C: 채팅방 목록 요청
    C->>API: GET /api/chatrooms
    API->>DB: 채팅방 목록 조회
    DB-->>API: 채팅방 목록 반환 (최근 메시지 포함)
    API-->>C: 채팅방 목록 응답
    C->>U: 채팅방 목록 표시 (최근 메시지 포함)
    Note over C,WS: 실시간 업데이트를 위한 WebSocket 연결
    C->>WS: WebSocket 연결 (/ws-connect)
    WS-->>C: 연결 완료

    Note over U,DB: 2. 채팅방 생성
    U->>C: 새 채팅방 생성 요청
    C->>API: POST /api/chatrooms
    API->>DB: 새 채팅방 정보 저장
    DB-->>API: 저장 완료
    API-->>C: 채팅방 생성 완료 응답
    C->>U: 새 채팅방 정보 표시

    Note over U,DB: 3. 채팅방 참여
    U->>C: 채팅방 입장 요청
		C->>MB: 채팅방 구독 요청 (STOMP 프레임: SUBSCRIBE /rooms/{roomId})
    MB-->>C: 구독 완료
    C->>WS: 채팅방 참여 메시지 (STOMP: SEND /app/join-room/{roomId})
    WS->>DB: 사용자 참여 정보 저장
    DB-->>WS: 저장 완료
    WS->>DB: 접속자 수 증가
    DB-->>WS: 업데이트 완료
    WS-->>C: 구독 완료, 참여 확인 및 접속자 수 전달 (STOMP MESSAGE)
    WS->>C: 채팅방 참여자들에게 새 참여자 알림 및 접속자 수 업데이트 브로드캐스트 (STOMP MESSAGE)
    C->>U: 채팅방 입장 완료 및 접속자 수 표시

    Note over U,DB: 4. 채팅 메시지 조회
    U->>C: 채팅 메시지 조회 요청
    C->>API: GET /api/chatrooms/{roomId}/messages
    API->>DB: 채팅 메시지 조회
    DB-->>API: 채팅 메시지 목록 반환
    API-->>C: 채팅 메시지 목록 응답
    C->>U: 채팅 메시지 목록 표시

    Note over U,DB: 5. 실시간 메시지 전송
    U->>C: 메시지 입력
    C->>WS: 메시지 전송 (STOMP: SEND /app/rooms/{roomId})
    WS->>DB: 메시지 저장
    DB-->>WS: 저장 완료
    WS->>C: 채팅방 구독자들에게 새 메시지 브로드캐스트 (STOMP MESSAGE to /rooms/{roomId})
    C->>U: 새 메시지 표시
    WS->>C: 채팅방 목록 구독자들에게 최근 메시지 업데이트 브로드캐스트 (STOMP MESSAGE to /rooms)
    C->>U: 채팅방 목록 업데이트 (최근 메시지)

    Note over U,DB: 6. 채팅방 나가기
    U->>C: 채팅방 나가기 요청
    C->>WS: 채팅방 나가기 메시지 (STOMP: SEND /app/chat/{roomId}/leave)
    WS->>DB: 사용자 참여 정보 업데이트
    DB-->>WS: 업데이트 완료
    WS->>C: 나가기 확인 전달 (STOMP MESSAGE)
    C->>WS: 채팅방 구독 해제 (STOMP: UNSUBSCRIBE)
    WS->>C: 다른 참여자들에게 접속자 수 업데이트 브로드캐스트 (STOMP MESSAGE to /rooms/{roomId})
    C->>U: 채팅방 나가기 완료
```
