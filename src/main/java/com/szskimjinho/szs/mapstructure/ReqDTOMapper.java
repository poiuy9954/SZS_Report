package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ReqLoginDTO;
import com.szskimjinho.szs.dto.ReqSignUpDTO;
import com.szskimjinho.szs.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReqDTOMapper {

    ReqSignUpDTO MemberDtoToReqSignUpDto(MemberDTO memberDTO);

    @Mapping(target = "memberKey", ignore = true)
    MemberDTO ReqSignUpDtoToMemberDto(ReqSignUpDTO reqSignUpDTO);

    @Mapping(target = "memberKey", ignore = true)
    @Mapping(target = "regNo", ignore = true)
    @Mapping(target = "name", ignore = true)
    MemberDTO ReqLoginDtoToMemberDto (ReqLoginDTO reqLoginDTO);
}
