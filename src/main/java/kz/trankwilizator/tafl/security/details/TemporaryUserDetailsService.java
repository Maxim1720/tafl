package kz.trankwilizator.tafl.security.details;

import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TemporaryUserDetailsService extends AbsUserDetailsService<TemporaryUser> {

    public TemporaryUserDetailsService(UserCrudService<TemporaryUser> temporaryUserUserCrudService) {
        super(temporaryUserUserCrudService);
    }

    @Override
    protected List<GrantedAuthority> authorities(String username) {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    protected String password(String username) {
        return "temp";
    }
}
