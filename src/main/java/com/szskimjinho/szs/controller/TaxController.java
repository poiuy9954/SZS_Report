package com.szskimjinho.szs.controller;

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
    @GetMapping("/refund")
    public String getTest(){
        return "asdasd";
    }

    @PostMapping("/scrap")
    public String szsScrap(@RequestHeader Map<String,String > header){
        log.info("TaxController::szsScrap");
        log.info("TaxController::szsScrap {}", header);
        scrapRuleService.scrapRule(header.get("authorization"));
        return "asdadsd";
    }
}
