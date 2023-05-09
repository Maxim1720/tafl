package kz.trankwilizator.tafl.config;

import kz.trankwilizator.tafl.security.filter.JwtAuthenticationFilter;
import kz.trankwilizator.tafl.security.details.PermanentUserDetailsService;
import kz.trankwilizator.tafl.security.temp.TempUserAuthenticationProvider;
import kz.trankwilizator.tafl.security.details.TempUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static String[] WHITE_LIST_URLS ={"/v3/api-docs/**","/swagger-ui/**", "/auth/**"};
    private final PermanentUserDetailsService permanentUserDetailsService;
    private final TempUserDetailsService tempUserDetailsService;
    private final JwtAuthenticationFilter[] jwtAuthenticationFilters;
    private final AuthenticationProvider[] authenticationProviders;

    public SecurityConfig(PermanentUserDetailsService permanentUserDetailsService,
                          TempUserDetailsService tempUserDetailsService,
                          JwtAuthenticationFilter[] jwtAuthenticationFilters,
                          AuthenticationProvider[] authenticationProviders) {
        this.permanentUserDetailsService = permanentUserDetailsService;
        this.tempUserDetailsService = tempUserDetailsService;
        this.jwtAuthenticationFilters = jwtAuthenticationFilters;
        this.authenticationProviders = authenticationProviders;
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
                .authenticationEntryPoint(basicAuthenticationEntryPoint());

        for (JwtAuthenticationFilter j: jwtAuthenticationFilters){
            httpSecurity = httpSecurity.addFilterBefore(j, UsernamePasswordAuthenticationFilter.class);
        }
        for (AuthenticationProvider a : authenticationProviders){
            httpSecurity.authenticationProvider(a);
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
        return new TempUserAuthenticationProvider(tempUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("myRealm");
        return basicAuthenticationEntryPoint;
    }

}
