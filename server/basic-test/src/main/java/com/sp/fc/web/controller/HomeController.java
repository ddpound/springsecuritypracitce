package com.sp.fc.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "메인 인덱스입니다.";
    }


    // 어떤 권한을 가지고 접근했는지 확인해보고 싶다면
    @GetMapping("/auth")
    public Authentication auth(){
        return SecurityContextHolder.getContext()
                .getAuthentication();
    }

    // 유저가 접근할 수 있는 페이지, 운영자가 접근할수 있는 페이지로 나눠보겠습니다.

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/user")
    public SecurityMessage user(){
        return SecurityMessage.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .message("User 정보")
                .build();
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public SecurityMessage admin(){
        return SecurityMessage.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .message("admin 정보")
                .build();
    }


}
