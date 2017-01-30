package com.speedment.example.securerest;

import com.speedment.example.securerest.db.SpeedmentApplication;
import com.speedment.example.securerest.db.SpeedmentApplicationBuilder;
import com.speedment.example.securerest.db.account.AccountManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Emil Forslund
 * @since  1.0.0
 */
@Configuration
public class SpeedmentConfiguration {

    private @Value("${dbms.host}") String host;
    private @Value("${dbms.port}") int port;
    private @Value("${dbms.schema}") String schema;
    private @Value("${dbms.username}") String username;
    private @Value("${dbms.password}") String password;
    
    @Bean
    public SpeedmentApplication getSpeedmentApplication() {
        return new SpeedmentApplicationBuilder()
            .withIpAddress(host)
            .withPort(port)
            .withUsername(username)
            .withPassword(password)
            .withSchema(schema)
            .build();
    }
    
    @Bean
    public AccountManager getAccountManager(SpeedmentApplication app) {
        return app.getOrThrow(AccountManager.class);
    }
    
}