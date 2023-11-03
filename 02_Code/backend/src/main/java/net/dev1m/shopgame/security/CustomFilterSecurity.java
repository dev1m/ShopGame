package net.dev1m.shopgame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {
    @Autowired
    CustomAuthService customAuthService;
    @Autowired
    CustomJwtFilter customJwtFilter;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customAuthService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
                 .cors().and()
                 .csrf().disable()
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authorizeHttpRequests()
                 .antMatchers(
                         "/auth/**",
                         "/category",
                         "/category/file/**",
                         "/category/get/title/**",
                         "/group",
                         "/group/file/**",
                         "/group/get/**",
                         "/group/getGroupId/**",
                         "/group/count/**",
                         "/category_caythue",
                         "/category_caythue/file/**",
                         "/category_caythue/get/title/**",
                         "/category_caythue/get/**",
                         "/group_caythue",
                         "/group_caythue/get/**",
                         "/group_caythue/count/**",
                         "/user/topNap",
                         "/account/get/**",
                         "/account/pt/**",
                         "/account/count/**",
                         "/account/file/**",
                         "/account/getAccountId/**",
                         "/bank/file/**",
                         "/option/getValue/**"
                 )
                 .permitAll()
                 .anyRequest()
                 .authenticated();
         http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
