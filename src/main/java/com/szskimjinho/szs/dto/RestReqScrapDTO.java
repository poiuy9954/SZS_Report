package com.szskimjinho.szs.dto;

import lombok.*;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestReqScrapDTO {
    private String name;
    private String regNo;
}
