package kz.trankwilizator.tafl.security.filter;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.security.details.TemporaryUserDetailsService;
import kz.trankwilizator.tafl.security.util.AuthenticationCreator;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import kz.trankwilizator.tafl.service.crud.user.TemporaryUserCrudService;
import kz.trankwilizator.tafl.service.jwt.JwtTokenService;
import org.springframework.stereotype.Component;

@Component
public class TemporaryUserAuthenticationFilter extends JwtAuthenticationFilter<TemporaryUser> {

    public TemporaryUserAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                             TemporaryUserDetailsService userDetailsService,
                                             RequestUtil requestUtil,
                                             AuthenticationCreator authenticationCreator,
                                             JwtTokenService jwtTokenService,
                                             TemporaryUserCrudService temporaryUserCrudService) {
        super(jwtTokenProvider,
                jwtTokenService, userDetailsService,
                requestUtil,
                authenticationCreator,
                temporaryUserCrudService
        );
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("/permanent/");
    }
}

