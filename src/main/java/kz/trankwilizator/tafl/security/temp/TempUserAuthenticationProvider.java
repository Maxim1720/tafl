package kz.trankwilizator.tafl.security.temp;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TempUserAuthenticationProvider implements AuthenticationProvider {

    private final TempUserDetailsService userDetailsService;

    public TempUserAuthenticationProvider(TempUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        // Проверяем, что username является временным (состоит только из цифр)
        if (!username.matches("\\d+")) {
            throw new BadCredentialsException("Invalid username");
        }

        // Получаем пользователя из базы данных
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // Проверяем, что пользователь существует
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new TempUserAuthenticationToken(username, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TempUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
