package com.szskimjinho.szs.controller;

import com.szskimjinho.szs.dto.ResRefundMsg;
import com.szskimjinho.szs.dto.ResScrapMsg;
import com.szskimjinho.szs.service.RefundRuleService;
import com.szskimjinho.szs.service.RefundService;
import com.szskimjinho.szs.service.ScrapRuleService;
import com.szskimjinho.szs.service.ScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/szs")
public class TaxController {

    private final ScrapRuleService scrapRuleService;
    private final RefundRuleService refundRuleService;
    @GetMapping("/refund")
    public ResRefundMsg getTest(@RequestHeader Map<String,String > header){
        return refundRuleService.getTaxAmount(header.get("authorization"));
    }

    @PostMapping("/scrap")
    public ResScrapMsg szsScrap(@RequestHeader Map<String,String > header){
        log.info("TaxController::szsScrap");
        log.info("TaxController::szsScrap {}", header);
        ResScrapMsg resScrapMsg = scrapRuleService.scrapRule(header.get("authorization"));
        return resScrapMsg;
    }
}
