package com.example.springboot.ad.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent abstractAuthenticationFailureEvent) {
        Authentication authentication = abstractAuthenticationFailureEvent.getAuthentication();
        log.info("Auth failed {}", abstractAuthenticationFailureEvent.getException());
    }
}
