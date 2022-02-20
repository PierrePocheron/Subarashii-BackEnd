package com.jfam.subarashii.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private RequestFilter requestFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
/*        http.addFilterBefore(jwtRequestFilter, JwtRequestFilter.class)
                .csrf()
                .ignoringAntMatchers("/auth/login");*/
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
*//*        http.csrf().disable().addFilterBefore(jwtRequestFilter, FilterSecurityInterceptor.class);

        http.authorizeRequests().antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated();*//*

        http.cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/auth/login\"").permitAll()
                .and()
                .csrf().disable();

        http.addFilterBefore(jwtRequestFilter, JwtRequestFilter.class);
    }*/
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/auth/login").permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }
*/

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers( "/auth/login").permitAll()
                //.antMatchers(HttpMethod.GET, "/posts/**").permitAll()
                //.antMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }*/
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/anime").hasRole("manager")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }*/
}
