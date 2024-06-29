package com.app.config;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")//Elimina esto en un futuro
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // @Autowired
    // AuthenticationConfiguration authenticationConfiguration;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    //     return httpSecurity
    //         .csrf(csrf -> csrf.disable())//Vulnerabilidad CSRF (Formularios)
    //         .httpBasic(Customizer.withDefaults()) //No requiere autenticacion
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //No guarda la sesion en memoria
    //         .authorizeHttpRequests(http -> {
    //             // Configurar los Endpoints publicos
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
                
    //             // Configurar los Endpoints privados
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("READ");
                
    //             // Configurar el resto de Endpoints -  NO ESPECIFICADOS
    //             http.anyRequest().denyAll(); // Rechaza todos los Endpoints no especificados
    //             // http.anyRequest().authenticated(); // Comprueba que el usuario este autenticado y autorizado
    //         })
    //         .build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())//Vulnerabilidad CSRF (Formularios)
            .httpBasic(Customizer.withDefaults()) //No requiere autenticacion
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //No guarda la sesion en memoria
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Podemos usar el AuthenticationConfiguration directamente como parametro o declararlo como Autowired
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(null);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Cambiar por BCryptPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }
}
