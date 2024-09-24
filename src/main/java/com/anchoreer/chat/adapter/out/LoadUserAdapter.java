package com.anchoreer.chat.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.LoadUserPort;
import com.anchoreer.chat.domain.entity.User;
import com.anchoreer.chat.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadUserAdapter implements LoadUserPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> load(Long userId) {
        return userRepository.findById(userId);
    }
}
