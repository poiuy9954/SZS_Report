package com.szskimjinho.szs.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {
    private String memberKey;
    private String userId;
    private String password;
    private String userName;
    private String regNo;
}
