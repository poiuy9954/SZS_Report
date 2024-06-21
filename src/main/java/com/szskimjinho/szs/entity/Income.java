package com.szskimjinho.szs.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
public class Income {

    @Id
    @UuidGenerator
    private String incomeKey;

    //이름 등 맴버정보
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    //세액공제 필드
    private String incomValue;
    private String craditTax;

    @Builder
    public Income(Member member, String incomValue,String craditTax) {
        this.member = member;
        this.incomValue = incomValue;
        this.craditTax=craditTax;
    }

    public Income() {

    }
}
