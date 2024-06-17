package com.szskimjinho.szs.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class AuthorizationMember {
    String name;
    String regNo;

    public boolean isAuthMember(MemberDTO memberDTO){
        return this.name.equals(memberDTO.getName()) && this.regNo.equals(memberDTO.getRegNo());
    }
}
