package com.szskimjinho.szs.controller;


import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import com.szskimjinho.szs.service.AuthorizationMemberService;
import com.szskimjinho.szs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/szs")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/getAuthSignupUser")
    public String getAuthSignupUser(){
        return "authUsers";
    }

    @PostMapping("/login")
    public String szsLogin(){
        return null;
    }

    @PostMapping("signup")
    public String szsSignUp(){
        memberService.saveAndChgOne(
                MemberDTO.builder()
                .memberKey(UUID.randomUUID().toString())
                .userId("Test1_ID")
                .password("Test1_PW")
                .userName("Test1_userName")
                .regNo("0000000000000")
                .build());

        return null;
    }
}
