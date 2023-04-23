package kz.trankwilizator.tafl.security.temp;

import kz.trankwilizator.tafl.crud.TemporaryUserCrudService;
import kz.trankwilizator.tafl.dao.user.temp.TempUserRepository;
import kz.trankwilizator.tafl.entity.user.temp.TemporaryUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class TempUserDetailsService implements UserDetailsService {

    private final TempUserRepository temporaryUserRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final TemporaryUserCrudService temporaryUserCrudService;

    public TempUserDetailsService(TempUserRepository temporaryUserRepository, JwtTokenProvider jwtTokenProvider, TemporaryUserCrudService temporaryUserCrudService) {
        this.temporaryUserRepository = temporaryUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.temporaryUserCrudService = temporaryUserCrudService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TemporaryUser tu = temporaryUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("%s doesn't exists", username)));
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority("USER"));
            }

            @Override
            public String getPassword() {
                return "temp";
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return tu.getEnabled() && tu.getExpiryAt().after(new Date());
            }

            @Override
            public boolean isAccountNonLocked() {
                return tu.getEnabled() && tu.getExpiryAt().after(new Date());
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return tu.getExpiryAt().after(new Date());
            }

            @Override
            public boolean isEnabled() {
                return tu.getEnabled();
            }
        };
    }

    public String authenticate(String username) {
        temporaryUserCrudService.getByUsername(username);
        return jwtTokenProvider.generateToken(username);
    }
    public boolean authorize(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            return true;
        }
        return false;
    }

}
