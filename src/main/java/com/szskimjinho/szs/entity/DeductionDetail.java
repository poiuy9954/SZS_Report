package com.szskimjinho.szs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@NoArgsConstructor
@Getter
//소득공제 월
public class DeductionDetail {

    @Id
    @UuidGenerator
    private String deductionDetailKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Income income;
    //신용카드, 국민연금 등 공제항목 종류
    private String deductionKind;
    //월별
    private String deductionMonth;
    //공제액
    private String deductionAmount;

    @Builder
    public DeductionDetail(Income income, String deductionKind, String deductionMonth, String deductionAmount) {
        this.income = income;
        this.deductionKind = deductionKind;
        this.deductionMonth = deductionMonth;
        this.deductionAmount = deductionAmount;
    }

    @AllArgsConstructor
    @Getter
    public enum DeductionKind{

        NAT("NAT","국민연금공제"),
        CDC("CDC","신용카드공제")
        ;
        private String kind;
        private String desc;
    }
}
