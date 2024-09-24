package com.anchoreer.chat.infrastructure.repository;

import static com.anchoreer.chat.domain.entity.QChat.*;
import static com.anchoreer.chat.domain.entity.QChatRoom.*;
import static com.anchoreer.chat.domain.entity.QChatRoomUser.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.anchoreer.chat.domain.entity.ChatRoomDto;
import com.anchoreer.chat.domain.entity.QChat;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomRepositoryCustomImpl implements ChatRoomRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ChatRoomDto> findAllChatRoomDto(Pageable pageable) {
        QChat subChat = new QChat("subChat");

        List<ChatRoomDto> content = jpaQueryFactory.select(Projections.constructor(ChatRoomDto.class,
                chatRoom.id, chatRoom.title, chatRoom.activeUserCount, chat.contents, chat.createdAt))
            .from(chatRoom)
            .innerJoin(chatRoomUser).on(chatRoom.id.eq(chatRoomUser.chatRoom.id))
            .leftJoin(chat).on(chat.id.eq(
                JPAExpressions.select(subChat.id.max())
                    .from(subChat)
                    .where(subChat.chatRoomUser.id.eq(chatRoomUser.id))
            ))
            .orderBy(chatRoom.activeUserCount.desc(), chat.createdAt.desc().nullsLast())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(chat.count())
            .from(chatRoom);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
