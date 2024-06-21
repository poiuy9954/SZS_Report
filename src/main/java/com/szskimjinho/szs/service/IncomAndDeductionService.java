package com.szskimjinho.szs.service;

import com.szskimjinho.szs.repository.DeductionDetailRepository;
import com.szskimjinho.szs.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class IncomAndDeductionService {

    private final IncomeRepository incomeRepository;
    private final DeductionDetailRepository deductionDetailRepository;


}
