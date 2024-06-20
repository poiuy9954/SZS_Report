package com.szskimjinho.szs.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.regex.Pattern;

public class Constant {

    public static final String REG_NO_REGEX = "^\\d{6}-\\d{7}$";
    public static final Pattern REG_NO_PATTERN = Pattern.compile(REG_NO_REGEX);
    public static final String DATE_FORMAT_yyMMdd = "yyMMdd";

    @AllArgsConstructor
    @Getter
    public enum ResDTO{
        S00000("S","가입을 축하드립니다.","S00000")
        ,F00001("F","가입가능목록에 존재하지 않습니다.","F00001")
        ,F00002("F","이미 가입한 ID입니다.","F00002")
        ,F00003("F", "값 유효성 체크에 실패하였습니다.","F00003")
        ;

        private final String code;
        private final String msg;
        private final String errCode;
    }

    @Getter
    @AllArgsConstructor
    public enum XAppKey{
        X_API_KEY("X-API-KEY","aXC8zK6puHIf9l53L8TiQg==")
        ;
        private String headerKey;
        private String key;
    }

    @Getter
    @AllArgsConstructor
    public enum Urls{
        SCRAP("https://codetest-v4.3o3.co.kr/scrap", HttpMethod.POST)
        ;
        String url;
        HttpMethod method;
    }
}
