package com.example.springboot.ad.config;

import com.example.springboot.ad.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.protocol}")
    private static String ldapProtocol;

    @Value("${ldap.url}")
    private static String ldapURL;

    @Value("${ldap.base}")
    private static String ldapBase;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "logout")
                .permitAll();
    }
    // avoid OU
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("cn={0}@WagerAD-test.net.local")
//                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://TULWAWDT001.WAGERAD-TEST.NET.LOCAL");
//                .url(ldapProtocol + ldapURL + ldapBase);
//                .ldif("classpath:users.ldif");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenService tokenService() {
        return new AuthTokenService();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
