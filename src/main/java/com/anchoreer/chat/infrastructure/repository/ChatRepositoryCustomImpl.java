package com.anchoreer.chat.infrastructure.repository;

import static com.anchoreer.chat.domain.entity.QChat.*;
import static com.anchoreer.chat.domain.entity.QChatRoom.*;
import static com.anchoreer.chat.domain.entity.QChatRoomUser.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.anchoreer.chat.domain.entity.Chat;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRepositoryCustomImpl implements ChatRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Chat> findAllByChatRoom_Id(long chatRoomId, Pageable pageable) {
        List<Chat> content = jpaQueryFactory.select(chat)
            .from(chatRoom)
            .innerJoin(chatRoomUser)
            .on(chatRoom.id.eq(chatRoomUser.chatRoom.id))
            .innerJoin(chat)
            .on(chatRoomUser.id.eq(chat.chatRoomUser.id))
            .orderBy(chat.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(chat.count())
            .from(chatRoom)
            .innerJoin(chatRoomUser)
            .on(chatRoom.id.eq(chatRoomUser.chatRoom.id))
            .innerJoin(chat)
            .on(chatRoomUser.id.eq(chat.chatRoomUser.id));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
