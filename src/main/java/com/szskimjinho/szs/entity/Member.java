package com.szskimjinho.szs.entity;

import com.szskimjinho.base.BaseEntity;
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

    private String userId;
    private String password;
    private String userName;
    private String regNo;

}
