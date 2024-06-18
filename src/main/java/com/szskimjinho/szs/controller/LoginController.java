package com.szskimjinho.szs.controller;


import com.szskimjinho.Constant.Constant;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ReqSignUpDTO;
import com.szskimjinho.szs.dto.ResDTO;
import com.szskimjinho.szs.entity.Member;
import com.szskimjinho.szs.mapstructure.ReqDTOMapper;
import com.szskimjinho.szs.service.AuthorizationMemberService;
import com.szskimjinho.szs.service.MemberService;
import com.szskimjinho.szs.service.SignUpMemberRuleService;
import jakarta.validation.constraints.NotNull;
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
    private final ReqDTOMapper reqDTOMapper;
    
    @GetMapping("/getAuthSignupUser")
    public String getAuthSignupUser(){
        return "authUsers";
    }

    @PostMapping("/login")
    public String szsLogin(){
        return null;
    }

    @PostMapping("/signup")
    public ResDTO szsSignUp( @RequestBody ReqSignUpDTO signUpDTO){
        if (!signUpDTO.chkValidation()) {
            ResDTO resDTO = new ResDTO();
            resDTO.setResDTO(Constant.ResDTO.F00003);
            return resDTO;
        }
        // TODO: 2024/06/18 주민번호 암호화? Base64..?
        return signUpMemberRuleService.registeMember(reqDTOMapper.ReqSignUpDtoToMemberDto(signUpDTO));
    }
}
