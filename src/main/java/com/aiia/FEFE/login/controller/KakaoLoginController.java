package com.aiia.FEFE.login.controller;

import com.aiia.FEFE.login.dto.KakaoCodeDto;
import com.aiia.FEFE.login.dto.KakaoSignUpDto;
import com.aiia.FEFE.login.dto.KakaoUserInfoResponseDto;
import com.aiia.FEFE.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @PostMapping("/kakaologin")
    public ResponseEntity<String> kakaoLogin(@RequestBody KakaoCodeDto request) {
        String authorizationCode = request.getCode();
        System.out.println("authorizationCode = " + authorizationCode);
        String accessToken = kakaoService.getAccessTokenFromKakao(authorizationCode);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        String jwtToken = kakaoService.createJwtToken(userInfo);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/kakaosignup")
    public ResponseEntity<String> signup(@RequestBody KakaoSignUpDto signUpDto) {
        kakaoService.registerUser(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }

}