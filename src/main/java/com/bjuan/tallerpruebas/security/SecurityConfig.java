package com.bjuan.tallerpruebas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/logout/**").permitAll()
                .antMatchers("/models/**").hasRole("ADMINISTRATOR")
                .antMatchers("/products/**").hasRole("ADMINISTRATOR")
                .antMatchers("/costhistory/**").hasRole("OPERATOR")
                .antMatchers("/shoppingcart/**").hasRole("OPERATOR")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

}