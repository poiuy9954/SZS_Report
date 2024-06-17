package com.szskimjinho.szs.service;

import com.szskimjinho.Constant.Constant;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpMemberRuleService {

    private final MemberService memberService;
    private final AuthorizationMemberService authorizationMemberService;




    public ResDTO/*추 후 가입응답객체 추가*/ registeMember(MemberDTO memberDTO){
        log.info("SignUpMemberRuleService::registeMember");
        log.debug("SignUpMemberRuleService::registeMember memberDTO {} ", memberDTO);
        /*
         * 가입절차 ->
         *   1. auth-member에 포함되는지 검사
         *   2. 기 가입자 인지 검사(ID중복 및 주민번호 중복 검사?)
         *   3. 가입 등록
         * */
        ResDTO resDTO = new ResDTO();
        //*   1. auth-member에 포함되는지 검사
        this.isAuthMember(resDTO,memberDTO);
        if (resDTO.isFaild())
            return resDTO;

        // *   2. 기 가입자 인지 검사(ID중복)
        this.isAlreadyRegisteMember(resDTO,memberDTO);
        if (resDTO.isFaild())
            return resDTO;

        this.memberService.saveAndChgOne(memberDTO);

        resDTO.setResDTO(Constant.ResDTO.S00000);
        return resDTO;
    }

    public void isAuthMember(ResDTO resDTO,MemberDTO memberDTO) {
        log.info("SignUpMemberRuleService::isAuthMember");
        boolean isAuthMember = authorizationMemberService.isAuthMember(memberDTO);/*목록에 없을때 false*/
        if (!isAuthMember) resDTO.setResDTO(Constant.ResDTO.F00001);
        log.debug("SignUpMemberRuleService::isAuthMember memberDTO {} , resDTO {}",memberDTO,resDTO);
    }
    public void isAlreadyRegisteMember(ResDTO resDTO,MemberDTO memberDTO){
        log.info("SignUpMemberRuleService::isAlreadyRegisteMember");
        boolean exist = memberService.existById(memberDTO);
        if(exist) resDTO.setResDTO(Constant.ResDTO.F00002);
        log.debug("SignUpMemberRuleService::isAlreadyRegisteMember memberDTO {} , resDTO {}",memberDTO,resDTO);
    }
}
