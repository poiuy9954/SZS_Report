package com.szskimjinho.szs.service;

import com.szskimjinho.szs.constant.Constant;
import com.szskimjinho.szs.dto.RestReqScrapDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class ScrapService {

    private final RestTemplate restTemplate;

    public void test(){
//        RestTemplate rest = new RestTemplate();
//        rest.postForEntity("https://codetest-v4.3o3.co.kr/scrap",)


    }

    public HashMap<String,String> sendScrapReq(RestReqScrapDTO restReqScrapDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(Constant.XAppKey.X_API_KEY.getHeaderKey(), Constant.XAppKey.X_API_KEY.getKey());

        HttpEntity<RestReqScrapDTO> entity = new HttpEntity<>(restReqScrapDTO,headers);
        log.debug("asdasdas :: {}",entity);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(Constant.Urls.SCRAP.getUrl(), Constant.Urls.SCRAP.getMethod(), entity,Map.class);
        log.debug("sendScrapReq :: {}",responseEntity.getBody());
        return new HashMap<>(responseEntity.getBody());
    }


}
