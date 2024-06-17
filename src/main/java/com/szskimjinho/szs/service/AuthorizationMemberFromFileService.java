package com.szskimjinho.szs.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szskimjinho.szs.dto.AuthorizationMember;
import com.szskimjinho.szs.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthorizationMemberFromFileService implements AuthorizationMemberService{


    @Value("${auth.member.path}")
    private Resource authMember;

    private String readJsonFile() {
        byte[] fileData = new byte[0];
        try {
            fileData = Files.readAllBytes(Paths.get(authMember.getURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(fileData);
    }

    @Override
    public boolean isAuthMember(MemberDTO memberDTO) {
        log.info("AuthorizationMemberFromFileService::isAuthMember");
        String authMember = readJsonFile();
        ArrayList<AuthorizationMember> authorizationMembers = new Gson().fromJson(authMember, new TypeToken<ArrayList<AuthorizationMember>>(){}.getType());
        return authorizationMembers.stream().anyMatch(e->e.isAuthMember(memberDTO));
    }
}
