package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.ReqLoginDTO;
import com.szskimjinho.szs.dto.ReqSignUpDTO;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import com.szskimjinho.szs.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReqDTOMapper {

    ReqSignUpDTO memberDtoToReqSignUpDto(MemberDTO memberDTO);

    @Mapping(target = "memberKey", ignore = true)
    MemberDTO reqSignUpDtoToMemberDto(ReqSignUpDTO reqSignUpDTO);

    @Mapping(target = "memberKey", ignore = true)
    @Mapping(target = "regNo", ignore = true)
    @Mapping(target = "name", ignore = true)
    MemberDTO reqLoginDtoToMemberDto(ReqLoginDTO reqLoginDTO);

    RestReqScrapDTO memberToRestReqScrapDTO(MemberDTO member);


}
