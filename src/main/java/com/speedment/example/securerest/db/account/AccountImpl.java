package com.speedment.example.securerest.db.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.speedment.example.securerest.db.account.generated.GeneratedAccountImpl;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

/**
 * The default implementation of the {@link
 * com.speedment.example.securerest.db.account.Account}-interface.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author speedment
 */
@JsonIgnoreProperties("password")
public final class AccountImpl extends GeneratedAccountImpl implements Account {

    private static final long serialVersionUID = -7552975849070084309L;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return createAuthorityList(getRole());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}