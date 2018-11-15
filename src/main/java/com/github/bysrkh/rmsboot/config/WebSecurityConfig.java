package com.github.bysrkh.rmsboot.config;

import com.github.bysrkh.rmsboot.repository.UserRepository;
import com.github.bysrkh.rmsboot.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .usernameParameter("username").passwordParameter("password")
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .and()
                .rememberMe().tokenValiditySeconds(600)
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl(userRepository));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
