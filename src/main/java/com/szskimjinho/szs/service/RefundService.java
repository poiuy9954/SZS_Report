package com.szskimjinho.szs.service;

import com.szskimjinho.szs.dto.DeductionDTO;
import com.szskimjinho.szs.dto.IncomeDTO;
import com.szskimjinho.szs.dto.IncomeDeductionDTO;
import com.szskimjinho.szs.mapstructure.DeductionDetailMapper;
import com.szskimjinho.szs.mapstructure.IncomeMapper;
import com.szskimjinho.szs.mapstructure.MemberMapper;
import com.szskimjinho.szs.repository.DeductionDetailRepository;
import com.szskimjinho.szs.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefundService {

    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final DeductionDetailMapper deductionDetailMapper;
    private final DeductionDetailRepository deductionDetailRepository;
    private final MemberMapper memberMapper;


    public IncomeDTO getIncome(String userId){
        log.info("RefundService::getIncome {}",userId);
        IncomeDTO incomeDTO = incomeMapper.toDto(incomeRepository.findByMember_UserId(userId));
        return incomeDTO;
    }

    public List<DeductionDTO> getDeductionDTOList(String incomeKey){
        log.info("RefundService::getIncome {}",incomeKey);
        return deductionDetailMapper.toDTOList(deductionDetailRepository.findByIncome_IncomeKey(incomeKey));
    }

    public static BigDecimal 산출세액(BigDecimal income) {

        BigDecimal tax = BigDecimal.ZERO;

        if (income.compareTo(new BigDecimal("14000000")) <= 0) {
            tax = income.multiply(new BigDecimal("0.06"));
        } else if (income.compareTo(new BigDecimal("50000000")) <= 0) {
            tax = new BigDecimal("840000").add(income.subtract(new BigDecimal("14000000")).multiply(new BigDecimal("0.15")));
        } else if (income.compareTo(new BigDecimal("88000000")) <= 0) {
            tax = new BigDecimal("6240000").add(income.subtract(new BigDecimal("50000000")).multiply(new BigDecimal("0.24")));
        } else if (income.compareTo(new BigDecimal("150000000")) <= 0) {
            tax = new BigDecimal("15360000").add(income.subtract(new BigDecimal("88000000")).multiply(new BigDecimal("0.35")));
        } else if (income.compareTo(new BigDecimal("300000000")) <= 0) {
            tax = new BigDecimal("37060000").add(income.subtract(new BigDecimal("150000000")).multiply(new BigDecimal("0.38")));
        } else if (income.compareTo(new BigDecimal("500000000")) <= 0) {
            tax = new BigDecimal("94060000").add(income.subtract(new BigDecimal("300000000")).multiply(new BigDecimal("0.40")));
        } else if (income.compareTo(new BigDecimal("1000000000")) <= 0) {
            tax = new BigDecimal("174060000").add(income.subtract(new BigDecimal("500000000")).multiply(new BigDecimal("0.42")));
        } else {
            tax = new BigDecimal("384060000").add(income.subtract(new BigDecimal("1000000000")).multiply(new BigDecimal("0.45")));
        }

        // 소수점 반올림
        tax = tax.setScale(0, RoundingMode.HALF_UP);

        return tax;
    }

    public BigDecimal 과세표준금액(IncomeDeductionDTO incomeDeductionDTO){
        BigDecimal 종합소득금액 = new BigDecimal(incomeDeductionDTO.getTotalIncome());

        List<BigDecimal>소득공제값List = incomeDeductionDTO.getDeductionDTOS().stream().map(e->new BigDecimal(e.getDeductionAmount().replace(",",""))).collect(Collectors.toList());
        log.debug("ㅁㄴㅇㅁㅇㅁㄴ ::; {}", 소득공제값List);
        BigDecimal 총소득공제값 = new BigDecimal(0);

        for (BigDecimal big:소득공제값List) {
            log.info("asdasd  :: {}", 총소득공제값);
            총소득공제값 = 총소득공제값.add(big);
        }
        log.debug("총소득공제값 : {}", 총소득공제값);
        return 종합소득금액.subtract(총소득공제값).setScale(0,RoundingMode.HALF_UP);
    }


    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("100.25");
        BigDecimal value2 = new BigDecimal("50.75");
        BigDecimal value3 = new BigDecimal("50.75");
        BigDecimal value4 = new BigDecimal("50.75");

        // 두 값의 합을 계산
        BigDecimal result = value1.add(value2);
        result.add(value3);

        // 결과 출력
        System.out.println("Result of addition: " + result);
    }
}
