package com.szskimjinho.szs.service;

import com.szskimjinho.szs.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpMemberService {

    private final MemberService memberService;
    private final AuthorizationMemberService authorizationMemberService;



}
