package com.szskimjinho.szs.service;

import com.szskimjinho.szs.dto.MemberDTO;


public interface AuthorizationMemberService {
    boolean isAuthMember(MemberDTO memberDTO);
}
