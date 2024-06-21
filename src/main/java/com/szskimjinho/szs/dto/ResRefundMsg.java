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
public class ResRefundMsg {
    String code;
    String msg;
    String errCode;
    String 결정세액;

    public void setSuccedMsg(BigDecimal 결정세액, RefundMsg refundMsg){
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        this.결정세액 = format.format(결정세액);
        this.code = refundMsg.getCode();
        this.msg = refundMsg.getMsg();
    }

    public void setFaildMsg(RefundMsg faildMsg){
        this.msg = faildMsg.getMsg();
        this.code = faildMsg.getCode();
        this.errCode = faildMsg.getErrCode();
    }

    @Getter
    @AllArgsConstructor
    public enum RefundMsg{
        S00000("S","계산완료","S00000"),
        F00001("F","존재하지않는 고객","F00001"),
        F00002("F","계산할 데이터가 없습니다.","F00002")
        ;

        private final String code;
        private final String msg;
        private final String errCode;
    }

}
