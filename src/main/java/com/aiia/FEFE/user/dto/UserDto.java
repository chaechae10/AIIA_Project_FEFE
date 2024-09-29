package com.aiia.FEFE.user.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private Long kakaoId;
    private String name;
    private String phoneNumber;
    private String department;

    public void setKakaoId(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}