package com.speedment.example.securerest.controller;

import com.speedment.example.securerest.db.account.Account;
import com.speedment.example.securerest.db.account.AccountImpl;
import com.speedment.example.securerest.db.account.AccountManager;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emil Forslund
 * @since  1.0.0
 */
@RestController
public class AccountController {

    private @Autowired AccountManager accounts;
    private @Autowired PasswordEncoder passwordEncoder;
    
    @PostMapping("/account")
    long onPostAccount(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        
        final Account created = accounts.persist(new AccountImpl()
            .setUsername(username)
            .setPassword(passwordEncoder.encode(password))
            .setRole("USER")
        );
        
        return created.getId();
    }
    
    @GetMapping("/account")
    List<Account> onGetAllAccounts() {
        return accounts.stream().collect(toList());
    }
    
    @GetMapping("/account/{id}")
    Account onGetAccount(
            @PathVariable("id") long accountId,
            Authentication auth) {
        
        final Account account = (Account) auth.getPrincipal();
        
        if (account.getId() == accountId) {
            return account;
        } else if ("ADMIN".equals(account.getRole())) {
            return accounts.stream()
                .filter(Account.ID.equal(accountId))
                .findAny().orElseThrow(NotFoundException::new);
        } else {
            throw new ForbiddenException();
        }
    }
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private final static class ForbiddenException extends RuntimeException {
        private static final long serialVersionUID = -4791719277573281730L;
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private final static class NotFoundException extends RuntimeException {
        private static final long serialVersionUID = 2071374314032968634L;
    }
}