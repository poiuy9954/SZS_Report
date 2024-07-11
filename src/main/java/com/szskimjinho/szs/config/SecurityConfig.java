package com.szskimjinho.szs.config;

import com.szskimjinho.szs.Utils.JWTUtils;
import com.szskimjinho.szs.filter.JwtAuthFilter;
import com.szskimjinho.szs.filter.PasswordEncryptFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf)->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authrize)->
                        authrize.requestMatchers(
                                        new AntPathRequestMatcher("/h2-console/**")
                                        ,new AntPathRequestMatcher("/3o3/**")
                                        ,new AntPathRequestMatcher("/swagger-ui/**")
                                        ,new AntPathRequestMatcher("/szs/login")
                                        ,new AntPathRequestMatcher("/szs/signup")
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .headers((headers)->headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .addFilterBefore(new PasswordEncryptFilter(passwordEncoder), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, PasswordEncryptFilter.class)

        ;
        return httpSecurity.build();
    }
}
