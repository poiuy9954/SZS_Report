package com.szskimjinho.szs.filter;

import com.google.gson.Gson;
import com.szskimjinho.szs.dto.ReqLoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PasswordEncryptFilter extends HttpFilter {

    private final PasswordEncoder passwordEncoder;
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (PasswordEncryptUrls.SIGNUP.getUrl().equals(request.getRequestURI())){
            log.debug("filter test {} ",request.getRequestURI());
            Gson gson = new Gson();
            PasswordEncryptServletRequestWrapper requestWrapper = new PasswordEncryptServletRequestWrapper(request);
            HashMap<String,String> bodyMap = gson.fromJson(requestWrapper.getRequestBody(), HashMap.class);

            String encryptPw = passwordEncoder.encode(bodyMap.get("password"));
            bodyMap.put("password",encryptPw);
            log.debug("modiReq {}", bodyMap);
            String modiRequest = gson.toJson(bodyMap);
            PasswordEncryptServletRequestWrapper modiRequestWrap = new PasswordEncryptServletRequestWrapper(new HttpServletRequestWrapper(request){
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(modiRequest.getBytes(StandardCharsets.UTF_8));
                    return new ServletInputStream() {
                        @Override
                        public boolean isFinished() {
                            return byteArrayInputStream.available()==0;
                        }

                        @Override
                        public boolean isReady() {
                            return true;
                        }

                        @Override
                        public void setReadListener(ReadListener listener) {

                        }

                        @Override
                        public int read() throws IOException {
                            return byteArrayInputStream.read();
                        }
                    };
                }

                @Override
                public BufferedReader getReader() throws IOException {
                    return new BufferedReader(new InputStreamReader(this.getInputStream(),StandardCharsets.UTF_8));
                }
            });
            super.doFilter(modiRequestWrap, response, chain);
        }
        super.doFilter(request, response, chain);
    }

    @Getter
    @AllArgsConstructor
    enum PasswordEncryptUrls {
        SIGNUP("/szs/signup"),
        LOGIN("/szs/login")
        ;
        private String url;
        public static List getAllUrls(){
            return Arrays.stream(PasswordEncryptUrls.values()).map(PasswordEncryptUrls::getUrl).toList();
        }
    }
}
