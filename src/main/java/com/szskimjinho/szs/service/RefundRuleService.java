package com.szskimjinho.szs.service;

import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundRuleService {

    private final IncomAndDeductionService incomAndDeductionService;
    private final MemberService memberService;
    private final RefundService refundService;
    private final JWTUtils jwtUtils;


    public ResRefundMsg getTaxAmount(String authorization){
        /*
        * 1. 맴버확인
        * 2. income조회
        * 3. incomeKey로 Dedu조회
        * 4. 계산
        *
        * */

        String userId = jwtUtils.getUserName(authorization.substring(7));

        MemberDTO memberDTO = memberService.getMemberByUserId(userId);
        log.debug("RefundRuleService::getTaxAmount member {}",memberDTO);
        if (memberDTO==null){
            ResRefundMsg resRefundMsg = new ResRefundMsg();
            resRefundMsg.setFaildMsg(ResRefundMsg.RefundMsg.F00001);
            return resRefundMsg;
        }

        IncomeDTO incomeDTO = refundService.getIncome(memberDTO.getUserId());
        if(incomeDTO==null){
            ResRefundMsg refundMsg = new ResRefundMsg();
            refundMsg.setFaildMsg(ResRefundMsg.RefundMsg.F00002);
            return refundMsg;
        }
        log.debug("RefundRuleService::getTaxAmount income {}",incomeDTO);

        List<DeductionDTO> deductionDTOS = refundService.getDeductionDTOList(incomeDTO.getIncomeKey());
        log.debug("RefundRuleService::getTaxAmount deductionDTOS {}",deductionDTOS);

        IncomeDeductionDTO incomeDeductionDTO = IncomeDeductionDTO.builder()
                .totalIncome(incomeDTO.getIncomValue())
                .craditTax(incomeDTO.getCraditTax())
                .memberDTO(memberDTO)
                .deductionDTOS(deductionDTOS)
                .build();

        log.debug("RefundRuleService::getTaxAmount incomeDeductionDTO {}",incomeDeductionDTO);

        BigDecimal basicTax = refundService.과세표준금액(incomeDeductionDTO);
        log.debug("RefundRuleService::getTaxAmount basicTax {}",basicTax);
        BigDecimal calculTax = refundService.산출세액(basicTax);
        log.debug("RefundRuleService::getTaxAmount calculTax {}",calculTax);

        BigDecimal craditTax = new BigDecimal(incomeDTO.getCraditTax().replace(",",""));
        BigDecimal lastTaxValue = calculTax.subtract(craditTax);

        log.debug("RefundService:: lastTaxValue :: {} ", lastTaxValue);

        ResRefundMsg resRefundMsg = new ResRefundMsg();
        resRefundMsg.setSuccedMsg(lastTaxValue, ResRefundMsg.RefundMsg.S00000);

        return resRefundMsg;
    }
}
