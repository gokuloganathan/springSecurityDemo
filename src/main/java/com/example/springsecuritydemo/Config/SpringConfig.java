package com.example.springsecuritydemo.Config;

import com.example.springsecuritydemo.Service.UserInfoUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity     /*enabling method level security for working preAuthorize annotation*/
public class SpringConfig {

    @Bean
    //authentication with user and admin cred
    public UserDetailsService userDetailsService(){
        /*UserDetails admin = User.withUsername("gokul")
                .password(encoder.encode("admin")).
                roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user1")
                .password(encoder.encode("user"))
                .roles("USER").build();

        */
        //return new InMemoryUserDetailsManager(admin,user);
        return new UserInfoUserDetailService();
    }

    //authorization for end points
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/date/welcome",
                        "/auth/**",
                        "/h2-console"
                ).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/date/**")
                .authenticated().and()
                .formLogin().and()
                .build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
