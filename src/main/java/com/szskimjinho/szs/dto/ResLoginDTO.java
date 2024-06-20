package com.szskimjinho.szs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szskimjinho.szs.constant.Constant;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ResLoginDTO {

    public ResLoginDTO(LoginMsg loginMsg){
        this.code=loginMsg.getCode();
        this.msg=loginMsg.getMsg();
        this.errCode=loginMsg.getErrCode();
    }

    String code;
    String msg;
    String errCode;
    String accessToken;

    public void setResDTO(LoginMsg loginMsg){
        this.code=loginMsg.getCode();
        this.msg=loginMsg.getMsg();
        this.errCode=loginMsg.getErrCode();
    }

    @JsonIgnore
    public boolean isFaild(){
        return "F".equals(this.code);
    }
    @JsonIgnore
    public boolean isSucced(){return "S".equals(this.code);}


    @Getter
    @AllArgsConstructor
    public enum LoginMsg{
        S00000("S","로그인성공","S00000"),
        F00001("F","로그인실패-ID,PW를 확인하세요","F00001"),
        F00002("F","로그인실패-입력값을 확인하세요","F00002")
        ;

        private final String code;
        private final String msg;
        private final String errCode;
    }

}
