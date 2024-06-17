package com.szskimjinho.szs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {
    @JsonIgnore
    private String memberKey;
    private String userId;
    private String password;
    private String name;
    private String regNo;
}
