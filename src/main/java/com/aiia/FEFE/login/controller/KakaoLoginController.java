package com.aiia.FEFE.login.controller;

import com.aiia.FEFE.login.dto.KakaoUserInfoResponseDto;
import com.aiia.FEFE.login.service.KakaoService;
import com.aiia.FEFE.user.dto.UserDto;
import com.aiia.FEFE.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @GetMapping("/page")
    public String loginPage(Model model) {
        //kakao 인증을 위한 url 생성
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri;
        model.addAttribute("location", location);
        return "login";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        log.info("Received authorization code: {}", code);

        //코드를 이용해서 액세스 토큰 받아옴
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        //액세스 토큰 사용해서 사용자 정보 가져옴
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // React 회원가입 페이지로 리다이렉트
        return "redirect:http://localhost:3000/register?kakaoId=" + userInfo.getId();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        log.info("카카오 아이디로 로그인: {}", userDto.getKakaoId());

        // KakaoUserInfoResponseDto에 kakaoId를 설정하고 나머지 정보를 받아 회원가입 처리
        KakaoUserInfoResponseDto kakaoUserInfo = new KakaoUserInfoResponseDto(userDto.getKakaoId());
        userService.loginOrRegister(kakaoUserInfo, userDto.getName(), userDto.getPhoneNumber(), userDto.getDepartment());

        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }
}