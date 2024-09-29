package com.aiia.FEFE.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long kakaoId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String department;

    public User(Long kakaoId, String name, String phoneNumber, String department) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }
}