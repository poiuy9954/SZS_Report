package com.szskimjinho.szs.service;

import com.google.gson.Gson;
import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import com.szskimjinho.szs.dto.ScrapResltDTO;
import com.szskimjinho.szs.mapstructure.ReqDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapRuleService {

    private final JWTUtils jwtUtils;
    private final MemberService memberService;
    private final ReqDTOMapper reqDTOMapper;
    private final ScrapService scrapService;

    public void scrapRule(String authorization) {
        log.info("ScrapRuleService::scrapRule");

        String userId = jwtUtils.getUserName(authorization.substring(7));
        log.info("asdasd  " + userId);

        MemberDTO memberDTO = memberService.getMemberByUserId(userId);
        log.info("asdasd" + memberDTO);

        RestReqScrapDTO restReqScrapDTO = reqDTOMapper.memberToRestReqScrapDTO(memberDTO);
        log.info("asdasd  " + restReqScrapDTO);

//        HashMap<String, String> hashMap = scrapService.sendScrapReq(restReqScrapDTO);
//        log.info("asdad   " + hashMap);
//
//        String json = new Gson().toJson(hashMap);
//        log.info("json sd {}", json);

        ScrapResltDTO scrapResltDTO = new Gson().fromJson(getJsonTest(), ScrapResltDTO.class);
//        ScrapResltDTO scrapResltDTO = new Gson().fromJson(json, ScrapResltDTO.class);
        log.info("ScrapResltDTO {} ", scrapResltDTO);

        scrapService.saveScrapRslt(scrapResltDTO,memberDTO);


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
