package com.szskimjinho.szs.service;

import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ResLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginMemberRuleService {

    private final MemberService memberService;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public ResLoginDTO loginMember(MemberDTO loginInfo){
        log.info("LoginMemberRuleService::loginMember");
        ResLoginDTO resLoginDTO = new ResLoginDTO();
        MemberDTO memberInfo = memberService.getMemberByUserId(loginInfo.getUserId());
        log.debug("LoginMemberRuleService::memberInfo {}",memberInfo);
        this.idChk(resLoginDTO,memberInfo);
        if (resLoginDTO.isFaild()) return resLoginDTO;

        this.pwChk(resLoginDTO,memberInfo, loginInfo);
        if (resLoginDTO.isFaild()) return resLoginDTO;

        resLoginDTO.setResDTO(ResLoginDTO.LoginMsg.S00000);
        resLoginDTO.setAccessToken(jwtUtils.genJwt(memberInfo.getUserId()));

        return resLoginDTO;
    }

    private void pwChk(ResLoginDTO resLoginDTO, MemberDTO memberInfo, MemberDTO loginInfo) {
        log.debug("LoginMemberRuleService::pwChk {} ",passwordEncoder.matches(loginInfo.getPassword(),memberInfo.getPassword()));
        if (!passwordEncoder.matches(loginInfo.getPassword(),memberInfo.getPassword())){
            resLoginDTO.setResDTO(ResLoginDTO.LoginMsg.F00001);
        }
    }

    private void idChk(ResLoginDTO resLoginDTO,MemberDTO loginMember) {
        if (loginMember ==null){
            resLoginDTO.setResDTO(ResLoginDTO.LoginMsg.F00001);
        }
    }
}
