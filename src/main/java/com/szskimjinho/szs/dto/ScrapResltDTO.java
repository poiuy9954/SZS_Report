package com.szskimjinho.szs.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ScrapResltDTO {

    private Data data;
    @Getter
    @Setter
    @ToString
    public static class Data {
        String 이름;
        String 종합소득금액;
        Taxminus 소득공제;
    }

    @Getter
    @Setter
    @ToString
    public static class Taxminus{
        List<Nps> 국민연금;
        CardTaxMinus 신용카드소득공제;
        String 세액공제;
    }
    @Getter
    @Setter
    @ToString
    public static class CardTaxMinus{
        String year;
        List<HashMap<String ,String>> month;

    }
    @Getter
    @Setter
    @ToString
    public static class Nps{
        String 월;
        String 공제액;
    }
}
