package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member toMemberEntity(MemberDTO memberDTO);
    MemberDTO toMemberDTO(Member member);

    List<Member> toEntityList(List<MemberDTO> list);
    List<MemberDTO> toDTOList(List<Member> list);
}
