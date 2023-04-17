package kz.trankwilizator.tafl.security;

import kz.trankwilizator.tafl.dao.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kz.trankwilizator.tafl.entity.User u = userRepository.findByLoginIgnoreCase(username).orElseThrow(
                ()-> new UsernameNotFoundException(username)
        );
        return new User(username, u.getPassword(), );
    }

    public Collection<GrantedAuthority> authorities(String username){
        userRepository.findByLoginIgnoreCase(username).get().
    }
}
