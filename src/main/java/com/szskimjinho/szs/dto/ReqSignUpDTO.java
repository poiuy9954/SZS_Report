package com.szskimjinho.szs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szskimjinho.Constant.Constant;
import com.szskimjinho.Utils.StringUtils;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReqSignUpDTO {
    private String userId;
    private String password;
    private String name;
    private String regNo;


    @JsonIgnore
    public boolean chkValidation(){
        return  StringUtils.isNotNone(userId)
                && StringUtils.isNotNone(password)
                && StringUtils.isNotNone(name)
                && StringUtils.isNotNone(regNo)
                && chkRegNoPatternAndBDayValid();
    }
    @JsonIgnore
    private boolean chkRegNoPatternAndBDayValid(){
        /*유효 주민번호 형식 체크*/
        boolean chkRegx= Constant.REG_NO_PATTERN.matcher(regNo).matches();
        if(!chkRegx) return false;

        /*유효 생년월일 체크*/
        char gender = regNo.charAt(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_yyMMdd);
        try{
            LocalDate bDay = LocalDate.parse(regNo.substring(0,6),formatter);
            int year = bDay.getYear();

            if      (gender == '1'|| gender == '2') year += 1900;
            else if (gender == '3' || gender == '4') year += 2000;
            else    return false;

            LocalDate.of(year,bDay.getMonthValue(),bDay.getDayOfMonth());
        }catch (Exception e){
            return false;
        }
        return true;

    }
}
