package kz.trankwilizator.tafl.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.security.JwtTokenProvider;
import kz.trankwilizator.tafl.security.details.AbsUserDetailsService;
import kz.trankwilizator.tafl.security.util.AuthenticationCreator;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import kz.trankwilizator.tafl.service.crud.user.UserCrudService;
import kz.trankwilizator.tafl.service.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


public abstract class JwtAuthenticationFilter<U extends User> extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenService jwtTokenService;
    private final AbsUserDetailsService<U> userDetailsService;
    private final RequestUtil requestUtil;
    private final AuthenticationCreator authenticationCreator;

    private final UserCrudService<U> userCrudService;

    @Value(value = "${auth.permit-all.paths}")
    private String[] whiteListPaths;

    @Value(value = "${auth.logout.path}")
    private String[] shouldFilterPaths;

    protected JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                      JwtTokenService jwtTokenService,
                                      AbsUserDetailsService<U> userDetailsService,
                                      RequestUtil requestUtil,
                                      AuthenticationCreator authenticationCreator,
                                      UserCrudService<U> userCrudService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.requestUtil = requestUtil;
        this.authenticationCreator = authenticationCreator;
        this.userCrudService = userCrudService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("request uri will be filtering: " + request.getRequestURI());

        String jwt = requestUtil.getJwtTokenFromRequest(request);
        if (!validateToken(jwt) || !userCrudService.existsByUsername(jwtTokenProvider.getUsernameFromToken(jwt))) {
            filterChain.doFilter(request, response);
            return;
        }
        authenticate(jwt, request);
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(String jwt){
        return StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt);
    }

    private void authenticate(String jwt, HttpServletRequest request) {
        String username = jwtTokenProvider.getUsernameFromToken(jwt);
        if (!jwtTokenService.isExpired(jwt)) {
            setAuthentication(username, request);
        }
    }

    private void setAuthentication(String username, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication auth = authenticationCreator.getUsernamePasswordAuthentication(userDetails, request);
        SecurityContextHolder.getContext().setAuthentication(auth);
        logger.info("Authenticated as: " + auth);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(whiteListPaths).anyMatch(
                (p)-> request.getRequestURI().startsWith(p.substring(0,p.lastIndexOf("/")))
        );
    }
}
