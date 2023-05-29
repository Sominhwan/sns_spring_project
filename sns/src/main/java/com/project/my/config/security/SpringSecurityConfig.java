package com.project.my.config.security;

import java.time.LocalDateTime;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }
    // 세션 생성, 만료, 변경 로고
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher(){
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher(){
            @Override
            public void sessionCreated(HttpSessionEvent event) {
                super.sessionCreated(event);
                System.out.printf("====> [%s] 세션 생성됨 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent event) {
                super.sessionDestroyed(event);
                System.out.printf("====> [%s] 세션 만료됨 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
                super.sessionIdChanged(event, oldSessionId);
                System.out.printf("====> [%s] 세션 아이디 변경 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }
        });
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin(); // iframe 스마트에디터 가능함
        http.authorizeHttpRequests()
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .antMatchers("/css/auth/**", "/images/**", "/js/auth/**", "/signUp","/termsService", "/signUpInfo","/signUpInfoCheck", "/emailCheck", "/emailHashCheck", "/signUpOk", "/findId", "/findUserId", "/findIdOk", "/findPwd", "/findUserPwd", "/findPwdChange", "/changeUserPwd", "/findPwdOk").permitAll()
                .antMatchers("/main").hasRole("USER") // USER 권환이 있는 경우만 해당 url 이용가능
                .antMatchers("/admin/**", "/admin/UserEmailSearch", "/css/admin/**", "/js/admin/**", "/adminImages/**", "/smarteditor/**").hasRole("ADMIN") // ADMIN 권환이 있는 경우만 해당 url 이용가능
                .anyRequest()
                .authenticated();
        http.sessionManagement()
                .maximumSessions(1) // 세션 개수 제한
                .maxSessionsPreventsLogin(true) // 동시 로그인 차단
                .expiredUrl("/sessionExpired"); // 세션 만료시 이동 url
        http.formLogin()
                .loginPage("/index") // 커스텀 로그인 페이지 지정
                .loginProcessingUrl("/login-process") // submit 받을 url
                .usernameParameter("userEmail") // submit할 아이디
                .passwordParameter("password") // submit할 비밀번호
                .defaultSuccessUrl("/loginOk.action") // 로그인 성공시 이동 url
                .permitAll();     
        http.oauth2Login()
                .loginPage("/index") // 소셜 로그인 페이지 지정
                .defaultSuccessUrl("/socialLoginOk.action", false)
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
        http.rememberMe()
                .key("oingdaddy!")
                .rememberMeParameter("remember")
                .tokenValiditySeconds(86400 * 30)
                .alwaysRemember(false)
                .userDetailsService(myUserDetailsService);
        http.logout()
            .logoutUrl("/logout") // 로그아웃 시 이동 url
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID", "remember") // 로그아웃시 쿠키, 세션 삭제
            .logoutSuccessHandler((request, response, authentication) -> {
                response.sendRedirect("/logOut.action");
            }); // 로그아웃 성공 핸들러      
        return http.build();
    }  
}
