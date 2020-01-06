/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Felipe
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    ServicioDeUsuarios identificacion;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(identificacion).passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication().withUser("admin").roles("ADMIN")
                .password("$2a$04$w98O4L2LMPWoTJ4CGX.cfeHMtOqY.o3b/qwBPo4Xab4sSREPyZijm")
                .and().passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/usuario").authenticated();
        httpSecurity.authorizeRequests().antMatchers("/usuario/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/**").hasAnyRole("", "ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
