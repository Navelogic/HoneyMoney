package br.com.honeymoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // Indica que é uma classe de configuração
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
}
