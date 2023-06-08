package kz.trankwilizator.tafl.config;

import jakarta.servlet.http.HttpServletResponse;
import kz.trankwilizator.tafl.security.authentication.entrypoint.UnauthorizedEntryPoint;
import kz.trankwilizator.tafl.security.authentication.provider.TempUserAuthenticationProvider;
import kz.trankwilizator.tafl.security.details.PermanentUserDetailsService;
import kz.trankwilizator.tafl.security.details.TemporaryUserDetailsService;
import kz.trankwilizator.tafl.security.logout.LogoutHandler;
import kz.trankwilizator.tafl.security.util.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@PropertySource("classpath:auth.properties")
public class SecurityConfig {

    @Value(value = "${auth.permit-all.paths}")
    private String[] WHITE_LIST_URLS;
    private final PermanentUserDetailsService permanentUserDetailsService;
    private final TemporaryUserDetailsService temporaryUserDetailsService;
    private final OncePerRequestFilter[] jwtAuthenticationFilters;
    private final AuthenticationProvider[] authenticationProviders;
    private final AuthenticationEntryPoint[] authEntryPoints;
    private final LogoutHandler logoutHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    public SecurityConfig(PermanentUserDetailsService permanentUserDetailsService,
                          TemporaryUserDetailsService temporaryUserDetailsService,
                          OncePerRequestFilter[] jwtAuthenticationFilters,
                          AuthenticationProvider[] authenticationProviders,
                          AuthenticationEntryPoint[] authEntryPoints,
                          LogoutHandler logoutHandler, LogoutSuccessHandler logoutSuccessHandler) {
        this.permanentUserDetailsService = permanentUserDetailsService;
        this.temporaryUserDetailsService = temporaryUserDetailsService;
        this.jwtAuthenticationFilters = jwtAuthenticationFilters;
        this.authenticationProviders = authenticationProviders;
        this.authEntryPoints = authEntryPoints;
        this.logoutHandler = logoutHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (r)-> r.requestMatchers(WHITE_LIST_URLS)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(new UnauthorizedEntryPoint(new RequestUtil()))
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        }))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(logoutConfigurer -> logoutConfigurer
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .logoutUrl("/auth/logout")
                        .invalidateHttpSession(true)
                )
                ;

        for (OncePerRequestFilter j: jwtAuthenticationFilters){
            httpSecurity = httpSecurity.addFilterBefore(j, UsernamePasswordAuthenticationFilter.class);
        }
        for (AuthenticationProvider a : authenticationProviders){
            httpSecurity = httpSecurity.authenticationProvider(a);
        }
        for (AuthenticationEntryPoint authenticationEntryPoint : authEntryPoints){
//            httpSecurity = httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and();
        }
        return httpSecurity.build();
    }


    @Bean
    public DaoAuthenticationProvider permanentUserAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(permanentUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public TempUserAuthenticationProvider temporaryUserAuthenticationProvider(){
        return new TempUserAuthenticationProvider(temporaryUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


}
