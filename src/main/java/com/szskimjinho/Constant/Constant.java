package com.szskimjinho.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Constant {

    @AllArgsConstructor
    @Getter
    public enum ResDTO{
        S00000("S","가입을 축하드립니다.","S00000")
        ,F00001("F","가입가능목록에 존재하지 않습니다.","F00001")
        ,F00002("F","이미 가입한 ID입니다.","F00002")
        ;

        private final String code;
        private final String msg;
        private final String errCode;
    }
}
