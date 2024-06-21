package com.szskimjinho.szs.dto;

import com.szskimjinho.szs.entity.Income;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class DeductionDTO {
    private String incomeKey;
    //신용카드, 국민연금 등 공제항목 종류
    private String deductionKind;
    //월별
    private String deductionMonth;
    //공제액
    private String deductionAmount;
}
