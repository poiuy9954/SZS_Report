package com.szskimjinho.szs.entity;

import com.szskimjinho.szs.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = false)
public class Member extends BaseEntity {

    @Id
    @UuidGenerator
    private String memberKey;

    @Column(unique = true)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String regNo;
    private String gender;
    private String bDay;



}
