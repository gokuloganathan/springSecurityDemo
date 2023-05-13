package com.example.springsecuritydemo.Config;

import com.example.springsecuritydemo.Filter.JwtAuthFilter;
import com.example.springsecuritydemo.Service.UserInfoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity     /*enabling method level security for working preAuthorize annotation*/
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;
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
        return new UserInfoUserDetailService();            //collects the data from db in the format of userdetails obj from actudal db record
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
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(
                        authFilter,
                         UsernamePasswordAuthenticationFilter.class
                ).build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());       //setting the authobj's userdetails collected from userinfouserdetailsservice
        authenticationProvider.setPasswordEncoder(passwordEncoder());           //setting the type of encoder, we are using bCrypt
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
