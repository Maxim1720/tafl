package kz.trankwilizator.tafl.config;

import kz.trankwilizator.tafl.security.authentication.provider.TempUserAuthenticationProvider;
import kz.trankwilizator.tafl.security.details.PermanentUserDetailsService;
import kz.trankwilizator.tafl.security.details.TemporaryUserDetailsService;
import kz.trankwilizator.tafl.security.logout.LogoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
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

    public SecurityConfig(PermanentUserDetailsService permanentUserDetailsService,
                          TemporaryUserDetailsService temporaryUserDetailsService,
                          OncePerRequestFilter[] jwtAuthenticationFilters,
                          AuthenticationProvider[] authenticationProviders,
                          AuthenticationEntryPoint[] authEntryPoints,
                          LogoutHandler logoutHandler) {
        this.permanentUserDetailsService = permanentUserDetailsService;
        this.temporaryUserDetailsService = temporaryUserDetailsService;
        this.jwtAuthenticationFilters = jwtAuthenticationFilters;
        this.authenticationProviders = authenticationProviders;
        this.authEntryPoints = authEntryPoints;
        this.logoutHandler = logoutHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        (r)-> r.requestMatchers(WHITE_LIST_URLS)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic()
                .and()
                .logout(logoutConfigurer -> logoutConfigurer.addLogoutHandler(logoutHandler).logoutUrl("/auth/logout"))
                ;

        for (OncePerRequestFilter j: jwtAuthenticationFilters){
            httpSecurity = httpSecurity.addFilterBefore(j, UsernamePasswordAuthenticationFilter.class);
        }
        for (AuthenticationProvider a : authenticationProviders){
            httpSecurity = httpSecurity.authenticationProvider(a);
        }
        for (AuthenticationEntryPoint authenticationEntryPoint : authEntryPoints){
            httpSecurity = httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and();
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
