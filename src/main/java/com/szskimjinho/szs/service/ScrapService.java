package com.szskimjinho.szs.service;

import com.szskimjinho.szs.Utils.StringUtils;
import com.szskimjinho.szs.constant.Constant;
import com.szskimjinho.szs.dto.MemberDTO;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import com.szskimjinho.szs.dto.ScrapResltDTO;
import com.szskimjinho.szs.entity.DeductionDetail;
import com.szskimjinho.szs.entity.Income;
import com.szskimjinho.szs.mapstructure.DeductionDetailMapper;
import com.szskimjinho.szs.mapstructure.MemberMapper;
import com.szskimjinho.szs.repository.DeductionDetailRepository;
import com.szskimjinho.szs.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ScrapService {

    private final RestTemplate restTemplate;
    private final MemberMapper memberMapper;
    private final DeductionDetailMapper deductionDetailMapper;
    private final IncomeRepository incomeRepository;
    private final DeductionDetailRepository deductionDetailRepository;


    public HashMap<String,String> sendScrapReq(RestReqScrapDTO restReqScrapDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(Constant.XAppKey.X_API_KEY.getHeaderKey(), Constant.XAppKey.X_API_KEY.getKey());
        ResponseEntity<Map> responseEntity = null;
        HttpEntity<RestReqScrapDTO> entity = new HttpEntity<>(restReqScrapDTO,headers);
        try {
            responseEntity = restTemplate.exchange(Constant.Urls.SCRAP.getUrl(), Constant.Urls.SCRAP.getMethod(), entity,Map.class);
        }catch (Exception e){
            return null;
        }
        if (responseEntity != null){
            log.debug("sendScrapReq :: {}",responseEntity.getBody());
            return new HashMap<>(responseEntity.getBody());
        }
        return null;
    }

    public void saveScrapRslt(ScrapResltDTO scrapResltDTO, MemberDTO memberDTO){

        ScrapResltDTO.Data data = scrapResltDTO.getData();
        if (data == null)
            return;
        if(!memberDTO.chkName(data.get이름()))
            return;

        Income income = this.saveIncome(memberDTO, data);

        ScrapResltDTO.Taxminus taxminus = data.get소득공제();
        if (taxminus==null)
            return;

        List<ScrapResltDTO.Nps> nps = taxminus.get국민연금();
        this.saveNps(income, nps);


        ScrapResltDTO.CardTaxMinus cardTaxMinus = taxminus.get신용카드소득공제();
        this.saveCardTaxMinus(income, cardTaxMinus);


    }

    private void saveCardTaxMinus(Income income, ScrapResltDTO.CardTaxMinus cardTaxMinus) {
        if (cardTaxMinus !=null){
            List<DeductionDetail> deductionDetails =
                deductionDetailMapper.cardTaxMinusToDeductionDetail(cardTaxMinus, income);
            deductionDetailRepository.saveAll(deductionDetails);
        }
    }

    public void saveNps(Income income, List<ScrapResltDTO.Nps> nps) {
        if (!CollectionUtils.isEmpty(nps)) {
//            국민연금 저장 -->deductionDetail 저장
            List<DeductionDetail> deductionDetails = nps.stream()
                .map(e->deductionDetailMapper.npsToDeductionDetail(e, income))
                .collect(Collectors.toList());
            deductionDetailRepository.saveAll(deductionDetails);
        }
    }

    public Income saveIncome(MemberDTO memberDTO, ScrapResltDTO.Data data) {
        Income income;
        String craditTax;
        if (data.get소득공제() != null)
            craditTax=data.get소득공제().get세액공제();
        else
            craditTax="0";
        income = Income.builder()
                .member(memberMapper.toMemberEntity(memberDTO))
                .incomValue(data.get종합소득금액())
                .craditTax(StringUtils.isNone(craditTax) ?"0":craditTax)
                .build();
        incomeRepository.save(income);
        return income;
    }


}
