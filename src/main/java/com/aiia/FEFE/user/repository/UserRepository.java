package com.aiia.FEFE.user.repository;

import com.aiia.FEFE.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByKakaoId(Long kakaoId);
}
