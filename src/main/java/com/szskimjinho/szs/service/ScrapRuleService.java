package com.szskimjinho.szs.service;

import com.google.gson.Gson;
import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ReqLoginDTO;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import com.szskimjinho.szs.dto.ScrapResltDTO;
import com.szskimjinho.szs.filter.JwtAuthFilter;
import com.szskimjinho.szs.mapstructure.ReqDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapRuleService {

    private final JWTUtils jwtUtils;
    private final MemberService memberService;
    private final ReqDTOMapper reqDTOMapper;
    private final ScrapService scrapService;
    public void scrapRule(String authorization){
        String userId = jwtUtils.getUserName(authorization.substring(7));
        log.info("asdasd  " + userId);

        MemberDTO memberDTO = memberService.getMemberByUserId(userId);
        log.info("asdasd" + memberDTO);

        RestReqScrapDTO restReqScrapDTO = reqDTOMapper.memberToRestReqScrapDTO(memberDTO);
        log.info("asdasd  " +restReqScrapDTO);

        HashMap<String,String> hashMap = scrapService.sendScrapReq(restReqScrapDTO);
        log.info("asdad   " + hashMap);

        String json = new Gson().toJson(hashMap);
        log.info("json sd {}",json );

        ScrapResltDTO scrapResltDTO = new Gson().fromJson(json,ScrapResltDTO.class);
        log.info("ScrapResltDTO {} ", scrapResltDTO);

    }

    public static void main(String[] args) {
        String aa= "{\"month\":[{\"01\":\"100,000.10\"},{\"03\":\"100,000.20\"},{\"05\":\"200,000.30\"},{\"10\":\"100,000\"},{\"12\":\"300,000.50\"}]}";
        HashMap<String,String > asda = new Gson().fromJson(aa,HashMap.class);
        System.out.println("adadads ::: "+asda);
    }
}
