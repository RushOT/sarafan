package com.remnant.sarafan.config;

import com.remnant.sarafan.domain.User;
import com.remnant.sarafan.repositories.UserDetailsRepository;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable();
    }
    
    public PrincipalExtractor principalExtractor(UserDetailsRepository userDetailsRepository) {
        return map -> {
            return new User();
        };
    }
}