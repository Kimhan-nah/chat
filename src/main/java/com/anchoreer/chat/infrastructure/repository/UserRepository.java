package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.chat.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
