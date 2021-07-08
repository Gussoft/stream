package com.gussoft.stream.security;

import com.gussoft.stream.config.CustomAccessDeniedHandler;
import com.gussoft.stream.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/error", "/forbidden","/layouts","/login","/usuario/registro"
                ,"/usuario/saved","/uploads/**", "/static/css/js/**","/css/**","/series/ver-serie/**","/cargar-serie", "/galery")
                .permitAll()
                .anyRequest().authenticated()
             .and()
             .formLogin().loginProcessingUrl("/auth").loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
             .and()
             .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }
}
