package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.Utils.StringUtils;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface MemberMapper extends toBase64QualfiedByName{

    @Mapping(source = "name",target = "userName")
    @Mapping(target = "bDay",source = "regNo", qualifiedByName = "tobDay")
    @Mapping(target = "gender",source = "regNo", qualifiedByName = "toGender")
    @Mapping(target = "regNo", source = "regNo", qualifiedByName = "toBase64Encode")
    Member toMemberEntity(MemberDTO memberDTO);

    @Mapping(source = "userName", target = "name")
    @Mapping(target = "regNo", source = "regNo", qualifiedByName = "toBase64Decode")
    MemberDTO toMemberDTO(Member member);

    List<Member> toEntityList(List<MemberDTO> list);
    List<MemberDTO> toDTOList(List<Member> list);


    @Named("tobDay")
    default String tobDay(String regNo) {
        if(StringUtils.isNone(regNo)) return null;
        return regNo.substring(0,6);
    }

    @Named("toGender")
    default String toGender(String regNo) {
        if(StringUtils.isNone(regNo)) return null;
        int genderInt = Integer.parseInt(String.valueOf(regNo.charAt(7)));
        return genderInt%2 < 1 ? "F":"M" ;
    }

}
