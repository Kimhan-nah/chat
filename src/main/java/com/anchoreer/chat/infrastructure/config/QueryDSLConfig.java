package com.anchoreer.chat.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Configuration
@EnableJpaAuditing
public class QueryDSLConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
