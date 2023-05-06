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
public class PermanentUserDetailsService extends AbsUserDetailsService<PermanentUser> {

    private final UserCrudService<PermanentUser> permanentUserUserCrudService;
    public PermanentUserDetailsService(UserCrudService<PermanentUser> permanentUserUserCrudService) {
        super(permanentUserUserCrudService);
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
}
