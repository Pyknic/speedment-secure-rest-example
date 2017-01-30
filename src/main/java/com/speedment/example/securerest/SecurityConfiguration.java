package com.speedment.example.securerest;

import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Emil Forslund
 * @since  1.0.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(POST, "/account").permitAll()
                .antMatchers(GET, "/account").hasAuthority("ROLE_ADMIN")
                .anyRequest().fullyAuthenticated()
            .and().httpBasic()
            .and().csrf().disable();
    }
}