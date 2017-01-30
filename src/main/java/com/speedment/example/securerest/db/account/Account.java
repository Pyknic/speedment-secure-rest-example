package com.speedment.example.securerest.db.account;

import com.speedment.example.securerest.db.account.generated.GeneratedAccount;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The main interface for entities of the {@code account}-table in the database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author speedment
 */
public interface Account extends GeneratedAccount, UserDetails {
    
    
}