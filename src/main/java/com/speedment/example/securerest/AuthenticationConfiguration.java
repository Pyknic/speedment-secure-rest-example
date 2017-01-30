package com.speedment.example.securerest;

import com.speedment.example.securerest.db.account.Account;
import com.speedment.example.securerest.db.account.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Emil Forslund
 * @since  1.0.0
 */
@Configuration
public class AuthenticationConfiguration 
extends GlobalAuthenticationConfigurerAdapter {

    private @Autowired AccountManager accounts;
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return username -> accounts.stream()
            .filter(Account.USERNAME.equal(username))
            .findAny()
            .orElseThrow(() -> new UsernameNotFoundException(
                "Could not find the user '" + username + "'"
            ));
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService())
            .passwordEncoder(getPasswordEncoder());
    }
}