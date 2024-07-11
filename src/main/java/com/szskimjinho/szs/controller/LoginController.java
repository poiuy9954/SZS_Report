package com.szskimjinho.szs.controller;


import com.szskimjinho.szs.constant.Constant;
import com.szskimjinho.szs.dto.ReqLoginDTO;
import com.szskimjinho.szs.dto.ReqSignUpDTO;
import com.szskimjinho.szs.dto.ResDTO;
import com.szskimjinho.szs.dto.ResLoginDTO;
import com.szskimjinho.szs.mapstructure.ReqDTOMapper;
import com.szskimjinho.szs.service.LoginMemberRuleService;
import com.szskimjinho.szs.service.SignUpMemberRuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/szs")
@RequiredArgsConstructor
public class LoginController {

    private final SignUpMemberRuleService signUpMemberRuleService;
    private final LoginMemberRuleService loginMemberRuleService;
    private final ReqDTOMapper reqDTOMapper;

    @PostMapping("/login")
    public ResLoginDTO szsLogin(@RequestBody ReqLoginDTO reqLoginDTO){
        log.debug("LoginController::szsLogin {}", reqLoginDTO);
        if (!reqLoginDTO.chkValidation()){
            ResLoginDTO resLoginDTO = new ResLoginDTO();
            resLoginDTO.setResDTO(ResLoginDTO.LoginMsg.F00002);
        }
        return loginMemberRuleService.loginMember(reqDTOMapper.reqLoginDtoToMemberDto(reqLoginDTO));
    }

    @PostMapping("/signup")
    public ResDTO szsSignUp( @RequestBody ReqSignUpDTO signUpDTO){
        log.debug("LoginController::szsSignUp {}",signUpDTO);
        if (!signUpDTO.chkValidation()) {
            ResDTO resDTO = new ResDTO();
            resDTO.setResDTO(Constant.ResDTO.F00003);
            return resDTO;
        }
        return signUpMemberRuleService.registeMember(reqDTOMapper.reqSignUpDtoToMemberDto(signUpDTO));
    }
}
