package com.szskimjinho.szs.controller;


import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ResDTO;
import com.szskimjinho.szs.entity.Member;
import com.szskimjinho.szs.service.AuthorizationMemberService;
import com.szskimjinho.szs.service.MemberService;
import com.szskimjinho.szs.service.SignUpMemberRuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/szs")
@RequiredArgsConstructor
public class LoginController {

    private final SignUpMemberRuleService signUpMemberRuleService;
    
    @GetMapping("/getAuthSignupUser")
    public String getAuthSignupUser(){
        return "authUsers";
    }

    @PostMapping("/login")
    public String szsLogin(){
        return null;
    }

    @PostMapping("signup")
    public ResDTO szsSignUp(@RequestBody MemberDTO memberDTO){
        // TODO: 2024/06/18 Validation 추가 필요함(memberDTO에 대한) 
        return signUpMemberRuleService.registeMember(memberDTO);
    }
}
