package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.Utils.StringUtils;
import com.szskimjinho.szs.dto.DeductionDTO;
import com.szskimjinho.szs.dto.ScrapResltDTO;
import com.szskimjinho.szs.entity.DeductionDetail;
import com.szskimjinho.szs.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;
import java.util.stream.Collectors;

@Mapper
public interface DeductionDetailMapper {

    default DeductionDetail npsToDeductionDetail(ScrapResltDTO.Nps nps, Income income){
        DeductionDetail.DeductionDetailBuilder deductionDetailBuilder = DeductionDetail.builder();

        return deductionDetailBuilder
                .income(income)
                .deductionKind(DeductionDetail.DeductionKind.NAT.getKind())
                .deductionMonth(nps.get월())
                .deductionAmount(nps.get공제액())
                .build();
    }

    default List<DeductionDetail> cardTaxMinusToDeductionDetail(ScrapResltDTO.CardTaxMinus cardTaxMinus, Income income){

        String year = cardTaxMinus.getYear();
        List<HashMap<String,String>> months = cardTaxMinus.getMonth();
        List<String> list =
                months.stream().map(e->
                        e.keySet().stream().map(k -> year+"-"+k+":"+e.get(k)).findFirst().get()).collect(Collectors.toList());
        List<DeductionDetail> details = new ArrayList<>();
        for (String deductionInfo:list) {
            String yyMM = (deductionInfo.split(":")[0]);
            yyMM = StringUtils.isNone(yyMM)?"":yyMM;
            String amount = deductionInfo.split(":")[1];
            amount = StringUtils.isNone(amount)?"":amount;

            DeductionDetail.DeductionDetailBuilder deductionDetailBuilder = DeductionDetail.builder();
            deductionDetailBuilder.deductionMonth(yyMM);
            deductionDetailBuilder.deductionAmount(amount);
            deductionDetailBuilder.deductionKind(DeductionDetail.DeductionKind.CDC.getKind());
            deductionDetailBuilder.income(income);
            details.add(deductionDetailBuilder.build());
        }
        return details;
    }

    @Mapping(source = "income.incomeKey",target = "incomeKey")
    DeductionDTO toDTO(DeductionDetail deductionDetail);

    List<DeductionDTO> toDTOList(List<DeductionDetail> deductionDetails);

}
