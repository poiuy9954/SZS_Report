package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Mapping(source = "name", target = "userName")
    Member toMemberEntity(MemberDTO memberDTO);
    @Mapping(source = "userName", target = "name")
    MemberDTO toMemberDTO(Member member);

    List<Member> toEntityList(List<MemberDTO> list);
    List<MemberDTO> toDTOList(List<Member> list);
}
