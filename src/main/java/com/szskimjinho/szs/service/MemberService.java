package com.szskimjinho.szs.service;

import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.entity.Member;
import com.szskimjinho.szs.mapstructure.MemberMapper;
import com.szskimjinho.szs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberDTO getMemberByUserId(String userId){
        log.info("MemberService::getMemberByUserId");
        return memberMapper.toMemberDTO(memberRepository.findByUserId(userId));
    }

    public boolean existById(MemberDTO memberDTO){
        log.info("MemberService::existById");
        log.debug("MemberService::existById memberDTO {} " , memberDTO);
        return memberRepository.existsByUserId(memberMapper.toMemberEntity(memberDTO).getUserId());
    }

    public MemberDTO getOneById(MemberDTO memberDTO){
        log.info("MemberService::getOneById()");
        return memberMapper.toMemberDTO(memberRepository.findById(memberDTO.getMemberKey()).orElse(new Member()));
    }

    public List<MemberDTO> getAll(){
        log.info("MemberService::getAll()");
        return memberMapper.toDTOList(memberRepository.findAll());
    }

    public void saveAndChgOne(MemberDTO memberDTO){
        log.info("MemberService::saveAndChgOne()");
        log.debug("MemberService::saveAndChgOne memberDTO {} ", memberDTO);
        memberRepository.save(
                memberMapper.toMemberEntity(memberDTO)
        );
    }


}
