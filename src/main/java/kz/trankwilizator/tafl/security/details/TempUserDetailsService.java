package kz.trankwilizator.tafl.security.details;

import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.crud.TemporaryUserCrudService;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TempUserDetailsService extends AbsUserDetailsService {

    @Override
    protected List<GrantedAuthority> authorities(String username) {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    protected String password(String username) {
        return "temp";
    }
/*
    private final UserRepository<TemporaryUser> temporaryUserRepository;

    public TempUserDetailsService(UserRepository<TemporaryUser> temporaryUserRepository) {
        this.temporaryUserRepository = temporaryUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TemporaryUser tu = temporaryUserRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format("%s doesn't exists", username))
                );
        return User.withUsername(tu.getUsername()).roles("USER").password("temp").build();
    }

    /*public String authenticate(String username) {
        temporaryUserCrudService.getByUsername(username);
        return jwtTokenProvider.generateToken(username);
    }
    public boolean authorize(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            return true;
        }
        return false;
    }*/

}
