package kz.trankwilizator.tafl.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.security.details.PermanentUserDetailsService;
import kz.trankwilizator.tafl.security.util.AuthenticationCreator;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import kz.trankwilizator.tafl.service.jwt.JwtTokenService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PermanentUserAuthenticationFilter extends JwtAuthenticationFilter<PermanentUser> {

    public PermanentUserAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                             PermanentUserDetailsService userDetailsService,
                                             RequestUtil requestUtil,
                                             AuthenticationCreator authenticationCreator,
                                             JwtTokenService jwtTokenService,
                                             UserCrudService<PermanentUser> userCrudService) {
        super(jwtTokenProvider,
                jwtTokenService, userDetailsService,
                requestUtil,
                authenticationCreator,
                userCrudService
        );
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("/temp/");
    }
}
