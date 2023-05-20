package com.project.my.config.security;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new SimplePasswordEncoder();
    // }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .antMatchers("/status", "/css/auth/**", "/images/**", "/js/auth/**", "/signUp","/termsService", "/signUpInfo","/signUpInfoCheck", "/emailCheck", "/emailHashCheck", "/signUpOk")
                .permitAll()
                .anyRequest()
                .authenticated()
        );
        http.formLogin(login -> login
                .loginPage("/index")	// [A] 커스텀 로그인 페이지 지정
                .loginProcessingUrl("/login-process")	// [B] submit 받을 url
                .usernameParameter("userEmail")	// [C] submit할 아이디
                .passwordParameter("password")	// [D] submit할 비밀번호
                .defaultSuccessUrl("/loginOk.action", false)
                .permitAll()
        );

        return http.build();
    }
    
}
