package kz.trankwilizator.tafl.security.temp;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class TempUserAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;

    public TempUserAuthenticationToken(String username) {
        super(Collections.emptyList());
        this.username = username;
    }

    public TempUserAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}

