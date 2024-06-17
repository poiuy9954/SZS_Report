package com.szskimjinho.szs.repository;

import com.google.gson.Gson;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.service.AuthorizationMemberFromFileService;
import com.szskimjinho.szs.service.AuthorizationMemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AuthorizationMemberServiceTests {

    @Autowired
    private AuthorizationMemberService authorizationMemberService;
    @Autowired
    private Gson gson;

    @Test
    public void isAuth(){
        MemberDTO memberDTO = this.getMemberDTO();
        log.debug("isAuth:memberDTO {} ",memberDTO);

        boolean b = authorizationMemberService.isAuthMember(memberDTO);
        log.debug("isAuth::After {} ", b);
    }

    private MemberDTO getMemberDTO(){
        String json ="{\n" +
                "\"userId\": \"kw68\",\n" +
                "\"password\": \"123456\",\n" +
                "\"name\": \"관우\",\n" +
                "\"regNo\": \"681108-1582816\"\n" +
                "}";
        return gson.fromJson(json,MemberDTO.class);
    }

}
