package kz.trankwilizator.tafl.security;

import kz.trankwilizator.tafl.dao.user.TempUserRepository;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.role.Permission;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final TempUserRepository tempUserRepository;
    public UserDetailsServiceImpl(UserRepository userRepository, TempUserRepository tempUserRepository) {
        this.userRepository = userRepository;
        this.tempUserRepository = tempUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AtomicReference<UserDetails> user = new AtomicReference<>();
        userRepository.findByUsernameIgnoreCase(username).ifPresentOrElse(u-> user.set(getByPermanentUser(u)),
                ()-> tempUserRepository.findByUsername(username).ifPresentOrElse(u->user.set(getByTempUser(u)),
                        ()->{throw new UsernameNotFoundException(username);})
        );
        return user.get();
    }

    private User getByPermanentUser(kz.trankwilizator.tafl.entity.user.User u){
        return new User(
                u.getUsername(),
                u.getPassword(),
                u.getEnabled(),
                u.getEnabled(),
                u.getEnabled(),
                u.getEnabled(),
                authorities(u));
    }

    private UserDetails getByTempUser(TemporaryUser user){
        return User.withUsername(user.getUsername()).roles("USER").password("").build();
    }

    public Collection<GrantedAuthority> authorities(String username){
        return userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(String.format("%s doesn't exists", username))
                )
                .getRole()
                .getPermissions()
                .stream()
                .map(
                        (Function<Permission, GrantedAuthority>) permission -> permission::getName)
                .toList();
    }

    private Collection<GrantedAuthority> authorities(kz.trankwilizator.tafl.entity.user.User user){
        return user
                .getRole()
                .getPermissions()
                .stream()
                .map(
                        (Function<Permission, GrantedAuthority>) permission -> permission::getName)
                .toList();
    }
}
