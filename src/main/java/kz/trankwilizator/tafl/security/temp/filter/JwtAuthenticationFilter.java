package kz.trankwilizator.tafl.security.temp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.crud.JwtTokenService;
import kz.trankwilizator.tafl.security.temp.JwtTokenProvider;
import kz.trankwilizator.tafl.security.temp.TempUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final TempUserDetailsService tempUserDetailsService;
    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                   TempUserDetailsService tempUserDetailsService,
                                   JwtTokenService jwtTokenService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tempUserDetailsService = tempUserDetailsService;
        this.jwtTokenService = jwtTokenService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            logger.info("jwt filtering request " + request.getRequestURI());
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                if (tempUserDetailsService.authorize(jwt)) {
                    String username = jwtTokenProvider.getUsernameFromToken(jwt);
                    if(jwtTokenService.getByToken(jwt).getExpiryAt().after(new Date())){
                        setAuthentication(username, request);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }

    private void setAuthentication(String username, HttpServletRequest request){
        UserDetails userDetails = tempUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
