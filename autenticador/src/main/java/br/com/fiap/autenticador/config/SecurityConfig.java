package br.com.fiap.autenticador.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests((requests) -> requests
                .requestMatchers("/", "/h2-console/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .failureUrl("/login?error=true") // URL para exibir mensagem de erro
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout") // URL para fazer logout
                .logoutSuccessUrl("/login?logout") // URL para redirecionar após o logout
                .permitAll()
            )
            .csrf().disable()
            .headers().frameOptions().disable(); // Necessário para o console H2

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}