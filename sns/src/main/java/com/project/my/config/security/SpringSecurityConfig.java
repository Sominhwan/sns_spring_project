package com.project.my.config.security;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .antMatchers("/status", "/css/auth/**", "/images/**", "/js/auth/**", "/signUp","/termsService", "/signUpInfo","/signUpInfoCheck", "/emailCheck", "/emailHashCheck", "/signUpOk", "/findId").permitAll()
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
        http.rememberMe()
                .key("oingdaddy!")
                .rememberMeParameter("remember")
                .tokenValiditySeconds(86400 * 30)
                .alwaysRemember(false)
                .userDetailsService(myUserDetailsService);
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED ) // 시큐리티가 세션 필요시 생성
                .maximumSessions(1) // 세션 개수 제한
                .expiredUrl("/logOut.action"); // 세션 만료시 이동 url
        http.logout()
            .logoutUrl("/logout") // 로그아웃 시 이동 url
            .deleteCookies("JSESSIONID", "remember") // 로그아웃시 쿠키, 세션 삭제
            .logoutSuccessHandler((request, response, authentication) -> {
                response.sendRedirect("/logOut.action");
            }); // 로그아웃 성공 핸들러      

        
        // 소셜 로그인 oauth2Login() 추가 
        // TODO
        return http.build();
    }  
}
