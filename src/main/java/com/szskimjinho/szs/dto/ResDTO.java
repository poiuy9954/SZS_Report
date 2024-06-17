package com.szskimjinho.szs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szskimjinho.Constant.Constant;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ResDTO {

    String code;
    String msg;
    String errCode;

    public void setResDTO(Constant.ResDTO resDTO){
        this.code=resDTO.getCode();
        this.msg=resDTO.getMsg();
        this.errCode=resDTO.getErrCode();
    }

    @JsonIgnore
    public boolean isFaild(){
        return "F".equals(this.code);
    }

}
