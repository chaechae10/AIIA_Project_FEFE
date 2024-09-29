package com.aiia.FEFE.user.service;

import com.aiia.FEFE.login.dto.KakaoUserInfoResponseDto;
import com.aiia.FEFE.user.domain.User;
import com.aiia.FEFE.user.dto.UserDto;
import com.aiia.FEFE.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User loginOrRegister(KakaoUserInfoResponseDto userInfo, String name, String phoneNumber, String department) {
        Long kakaoId = userInfo.getId();

        Optional<User> existingUser = userRepository.findByKakaoId(kakaoId);

        return existingUser.orElseGet(() -> {
            User newUser = new User(kakaoId, name, phoneNumber, department);
            return userRepository.save(newUser);
        });
    }
}