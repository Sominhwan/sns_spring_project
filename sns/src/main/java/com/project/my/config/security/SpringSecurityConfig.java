package com.project.my.config.security;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .antMatchers("/status", "/css/auth/**", "/images/**", "/js/auth/**", "/signUp","/termsService", "/signUpInfo","/signUpInfoCheck", "/emailCheck", "/emailHashCheck", "/signUpOk").permitAll()
                .antMatchers("/main").hasRole("USER") // USER 권환이 있는 경우만 해당 url 이용가능
                //.antMatchers("").hasRole("ADMIN") // ADMIN 권한이 있는 경우만 해당 url 이용가능
                .anyRequest()
                .authenticated()
        );
        http.formLogin(login -> login
                .loginPage("/index") // 커스텀 로그인 페이지 지정
                .loginProcessingUrl("/login-process") // submit 받을 url
                .usernameParameter("userEmail") // submit할 아이디
                .passwordParameter("password") // submit할 비밀번호
                .defaultSuccessUrl("/loginOk.action", false) // 로그인 성공시 이동 url
                .permitAll()
        );
        // 소셜 로그인 oauth2Login() 추가 
        // TODO
        return http.build();
    }  
}
