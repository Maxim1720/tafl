package kz.trankwilizator.tafl.security.filter;

import kz.trankwilizator.tafl.security.details.PermanentUserDetailsService;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import org.springframework.stereotype.Component;

@Component
public class PermanentUserAuthenticationFilter extends JwtAuthenticationFilter {

    public PermanentUserAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                             JwtTokenCrudService jwtTokenCrudService,
                                             PermanentUserDetailsService userDetailsService) {
        super(jwtTokenProvider, jwtTokenCrudService, userDetailsService);
    }
}
