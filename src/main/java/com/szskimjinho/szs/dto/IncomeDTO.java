package com.szskimjinho.szs.dto;

import com.szskimjinho.szs.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class IncomeDTO {
    private String incomeKey;
    //이름 등 맴버정보
    private Member member;
    //세액공제 필드
    private String incomValue;
    private String craditTax;

}
