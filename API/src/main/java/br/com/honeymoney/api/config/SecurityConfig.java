package br.com.honeymoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration // Indica que é uma classe de configuração
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configura autenticação em memória com usuário e senha
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ROLE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/categorys").permitAll() // Permite acesso sem autenticação à URL "/api/categorys"
                .anyRequest().authenticated() // Exige autenticação para qualquer outra URL
                .and()
                .httpBasic() // Configura autenticação básica
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura política de criação de sessão como stateless (sem estado)
                .and()
                .csrf().disable(); // Desabilita proteção CSRF (Cross-Site Request Forgery)

    }
}
