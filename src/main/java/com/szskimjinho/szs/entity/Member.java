package com.szskimjinho.szs.entity;

import com.szskimjinho.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = false)
public class Member extends BaseEntity {

    @Id
    private String memberKey;

    private String userId;
    private String password;
    private String userName;
    private String regNo;

}
