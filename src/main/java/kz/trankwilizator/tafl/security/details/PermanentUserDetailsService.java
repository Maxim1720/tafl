package kz.trankwilizator.tafl.security.details;

import kz.trankwilizator.tafl.entity.role.Permission;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermanentUserDetailsService extends AbsUserDetailsService/*<PermanentUser>*/ {

    private final UserCrudService<PermanentUser> permanentUserUserCrudService;
    public PermanentUserDetailsService(UserCrudService<PermanentUser> permanentUserUserCrudService) {
        this.permanentUserUserCrudService = permanentUserUserCrudService;
    }

    @Override
    protected List<GrantedAuthority> authorities(String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Permission p : permanentUserUserCrudService.getByUsername(username).getRole().getPermissions()){
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        return authorities;
    }

    @Override
    protected String password(String username) {
        return permanentUserUserCrudService.getByUsername(username).getPassword();
    }

   /* private final UserRepository<PermanentUser> userRepository;

    public PermanentUserDetailsService(UserRepository<PermanentUser> userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AtomicReference<UserDetails> user = new AtomicReference<>();
        userRepository.findByUsernameIgnoreCase(username)
                .ifPresentOrElse(
                        u-> user.set(getByPermanentUser(u)), ()->{
                            throw new UsernameNotFoundException(String.format("%s doesn't exists", username));
                        } );
        return user.get();
    }

    private User getByPermanentUser(PermanentUser u){
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

    private Collection<GrantedAuthority> authorities(PermanentUser permanentUser){
        return permanentUser
                .getRole()
                .getPermissions()
                .stream()
                .map(
                        (Function<Permission, GrantedAuthority>) permission -> permission::getName)
                .toList();
    }*/
}
