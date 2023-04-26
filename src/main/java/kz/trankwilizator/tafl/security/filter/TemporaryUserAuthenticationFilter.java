package kz.trankwilizator.tafl.security.filter;

import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.security.details.TempUserDetailsService;
import kz.trankwilizator.tafl.service.crud.JwtTokenCrudService;
import org.springframework.stereotype.Component;

@Component
public class TemporaryUserAuthenticationFilter extends JwtAuthenticationFilter {
    public TemporaryUserAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                             JwtTokenCrudService jwtTokenCrudService,
                                             TempUserDetailsService userDetailsService) {
        super(jwtTokenProvider, jwtTokenCrudService, userDetailsService);
    }
}

