package kz.trankwilizator.tafl.security.details;

import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public abstract class AbsUserDetailsService<U extends User> implements UserDetailsService {

    private final UserCrudService<U> userCrudService;

    public AbsUserDetailsService(UserCrudService<U> userCrudService) {
        this.userCrudService = userCrudService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password(username))
                .authorities(authorities(username))
                .build();
    }

    protected abstract List<GrantedAuthority> authorities(String username);
    protected abstract String password(String username);
}
