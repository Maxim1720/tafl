package kz.trankwilizator.tafl.config;

import kz.trankwilizator.tafl.auth.AuthService;
import kz.trankwilizator.tafl.auth.RegistrationService;
import kz.trankwilizator.tafl.auth.TemporaryUserService;
import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.logic.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        (r)-> r.requestMatchers("/v3/api-docs/**","/swagger-ui/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic()
                .and()
                .build();
    }


    @Bean
    public RegistrationService registrationService(){
        return new RegistrationService() {
            @Override
            public PermanentUserDto createUser(PermanentUserDto user) {
                return null;
            }
        };
    }

    @Bean
    public TemporaryUserService temporaryUserService(){
        return new TemporaryUserService() {
            @Override
            public TemporaryUserDto create() {
                return null;
            }

            @Override
            public TemporaryUserDto replenishBalance(Double amount) {
                return null;
            }
        };
    }

    @Bean
    public PaymentService paymentService(){
        return new PaymentService() {
            @Override
            public void processPayment() {

            }
        };
    }

    @Bean
    public AuthService authService(){
        return absUserDto -> null;
    }

}
