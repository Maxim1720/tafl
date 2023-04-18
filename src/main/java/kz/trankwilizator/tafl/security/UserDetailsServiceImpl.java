package kz.trankwilizator.tafl.security;

import kz.trankwilizator.tafl.dao.UserRepository;
import kz.trankwilizator.tafl.entity.role.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kz.trankwilizator.tafl.entity.user.User u = userRepository.findByLoginIgnoreCase(username).orElseThrow(
                ()-> new UsernameNotFoundException(username)
        );
        return new User(
                username,
                u.getPassword(),
                u.getEnabled(),
                u.getEnabled(),
                u.getEnabled(),
                u.getEnabled(),
                authorities(username));
    }

    public Collection<GrantedAuthority> authorities(String username){
        return userRepository
                .findByLoginIgnoreCase(username)
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
}
