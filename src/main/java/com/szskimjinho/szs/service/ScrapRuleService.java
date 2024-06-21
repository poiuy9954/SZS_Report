package com.szskimjinho.szs.service;

import com.google.gson.Gson;
import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ResScrapMsg;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import com.szskimjinho.szs.dto.ScrapResltDTO;
import com.szskimjinho.szs.mapstructure.ReqDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapRuleService {

    private final JWTUtils jwtUtils;
    private final MemberService memberService;
    private final ReqDTOMapper reqDTOMapper;
    private final ScrapService scrapService;

    public ResScrapMsg scrapRule(String authorization) {
        log.info("ScrapRuleService::scrapRule");

        String userId = jwtUtils.getUserName(authorization.substring(7));


        MemberDTO memberDTO = memberService.getMemberByUserId(userId);
        if(memberDTO==null){
            ResScrapMsg resScrapMsg = new ResScrapMsg();
            resScrapMsg.setMsg(ResScrapMsg.RefundMsg.F00001);
            return resScrapMsg;
        }

        RestReqScrapDTO restReqScrapDTO = reqDTOMapper.memberToRestReqScrapDTO(memberDTO);


        HashMap<String, String> hashMap = scrapService.sendScrapReq(restReqScrapDTO);
        if (CollectionUtils.isEmpty(hashMap)){
            ResScrapMsg resScrapMsg = new ResScrapMsg();
            resScrapMsg.setMsg(ResScrapMsg.RefundMsg.F00002);
            return resScrapMsg;
        }
        String json = new Gson().toJson(hashMap);
        ScrapResltDTO scrapResltDTO = new Gson().fromJson(json, ScrapResltDTO.class);

//        0621 502에러로 접근이 안되어 임시조치
//        ScrapResltDTO scrapResltDTO = new Gson().fromJson(getJsonTest(), ScrapResltDTO.class);
        log.debug("ScrapResltDTO {} ", scrapResltDTO);

        scrapService.saveScrapRslt(scrapResltDTO,memberDTO);

        ResScrapMsg resScrapMsg = new ResScrapMsg();
        resScrapMsg.setMsg(ResScrapMsg.RefundMsg.S00000);
        return resScrapMsg;
    }

    private String getJsonTest(){
        String json = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"data\": {\n" +
                "    \"종합소득금액\": 20000000,\n" +
                "    \"이름\": \"동탁\",\n" +
                "    \"소득공제\": {\n" +
                "      \"국민연금\": [\n" +
                "        {\n" +
                "          \"월\": \"2023-01\",\n" +
                "          \"공제액\": \"300,000.25\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-02\",\n" +
                "          \"공제액\": \"200,000\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-03\",\n" +
                "          \"공제액\": \"400,000.75\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-05\",\n" +
                "          \"공제액\": \"100,000.10\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-06\",\n" +
                "          \"공제액\": \"300,000\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-08\",\n" +
                "          \"공제액\": \"200,000.20\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-09\",\n" +
                "          \"공제액\": \"300,000.40\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-10\",\n" +
                "          \"공제액\": \"300,000.70\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-11\",\n" +
                "          \"공제액\": \"0\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"월\": \"2023-12\",\n" +
                "          \"공제액\": \"0\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"신용카드소득공제\": {\n" +
                "        \"month\": [\n" +
                "          {\n" +
                "            \"01\": \"100,000.10\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"03\": \"100,000.20\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"05\": \"200,000.30\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"10\": \"100,000\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"12\": \"300,000.50\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"year\": 2023\n" +
                "      },\n" +
                "      \"세액공제\": \"300,000\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"errors\": {\n" +
                "    \"code\": null,\n" +
                "    \"message\": null,\n" +
                "    \"validations\": null\n" +
                "  }\n" +
                "}";
        return json;
    }
}
