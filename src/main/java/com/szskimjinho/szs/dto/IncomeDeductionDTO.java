package com.szskimjinho.szs.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class IncomeDeductionDTO {

    private String craditTax;
    private String totalIncome;
    private MemberDTO memberDTO;

    private List<DeductionDTO> deductionDTOS;

}
