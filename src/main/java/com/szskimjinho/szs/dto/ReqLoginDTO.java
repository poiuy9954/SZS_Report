package com.szskimjinho.szs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szskimjinho.szs.Utils.StringUtils;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ReqLoginDTO {

    String userId;
    String password;

    @JsonIgnore
    public boolean chkValidation(){
        return  StringUtils.isNotNone(userId)
                && StringUtils.isNotNone(password);
     }
}
