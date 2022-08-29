package com.asusoftware.myTransporter.security;

import com.asusoftware.myTransporter.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/user/login")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user/create")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/post/create/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/post/findAll")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/post/like-post/**")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/post/unlike-post/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user/updateImageProfile/**")
                .permitAll()
               // .antMatchers(HttpMethod.POST, "/api/v1/user/**")
               // .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/address/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/user/findAll")
                .hasAuthority("TRANSPORTER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}