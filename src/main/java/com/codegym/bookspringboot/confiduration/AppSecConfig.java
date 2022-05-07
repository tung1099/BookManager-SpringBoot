package com.codegym.bookspringboot.confiduration;

import com.codegym.bookspringboot.service.appuser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAppUserService appUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                ((UserDetailsService) appUserService)
        ).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/home").permitAll()
                .and()
                .authorizeRequests().antMatchers("/","/**search**","/category","/view-category/**").hasAnyRole("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/delete-book/**","/edit-book/**","/create-book/**",
                        "/delete-category/**","/edit-category/**","/create-category/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
//                .formLogin().loginProcessingUrl(String.valueOf(new AntPathRequestMatcher("/login")))
//                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}
