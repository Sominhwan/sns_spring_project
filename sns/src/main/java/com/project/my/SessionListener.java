package com.project.my;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;

@Configuration

public class SessionListener implements HttpSessionListener {
    //application.yml에 session.timeout 설정.

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(1800);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    
}
