package com.anchoreer.chat.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_user_id")
    private ChatRoomUser chatRoomUser;

    @NotNull
    @Size
    private String contents;

    @Builder
    public Chat(Long id, ChatRoomUser chatRoomUser, String contents) {
        this.id = id;
        this.chatRoomUser = chatRoomUser;
        this.contents = contents;
    }
}
