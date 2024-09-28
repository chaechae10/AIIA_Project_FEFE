package com.aiia.FEFE.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long kakaoId;
    private String name;
    private String phoneNumber;
    private String department;
}