package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void toEntity(){
        Member member = memberMapper.toMemberEntity(
                MemberDTO.builder()
                        .memberKey(UUID.randomUUID().toString())
                        .userId("Test1_ID")
                        .password("Test1_PW")
                        .name("Test1_userName")
                        .regNo("910424-1234567")
                        .build()
        );
        log.debug(String.valueOf(member));
        Assert.isTrue(member.getUserId() == "Test1_ID","성공");
    }

    @Test
    public void toDTO(){
        MemberDTO memberDTO = memberMapper.toMemberDTO(
                Member.builder()
                        .memberKey(UUID.randomUUID().toString())
                        .userId("Test2_ID")
                        .password("Test2_PW")
                        .userName("Test2_userName")
                        .regNo("0000000000000")
                        .build()
        );
        log.debug(String.valueOf(memberDTO));
        Assert.isTrue(memberDTO.getUserId() == "Test2_ID","성공");
    }



}
