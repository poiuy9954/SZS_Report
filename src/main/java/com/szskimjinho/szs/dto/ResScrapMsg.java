package com.szskimjinho.szs.dto;

import lombok.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class ResScrapMsg {
    String code;
    String msg;
    String errCode;


    public void setMsg(RefundMsg faildMsg){
        this.msg = faildMsg.getMsg();
        this.code = faildMsg.getCode();
        this.errCode = faildMsg.getErrCode();
    }

    @Getter
    @AllArgsConstructor
    public enum RefundMsg{
        S00000("S","실행완료","S00000"),
        F00001("F","존재하지않는 고객","F00001"),
        F00002("F","연동실패 서버상태 확인","F00002")
        ;

        private final String code;
        private final String msg;
        private final String errCode;
    }

}
