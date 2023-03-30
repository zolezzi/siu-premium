package unq.edu.li.pdes.unqpremium.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import unq.edu.li.pdes.unqpremium.model.User;
import unq.edu.li.pdes.unqpremium.repository.UserRepository;
import unq.edu.li.pdes.unqpremium.security.JWTAuthorizationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final UserRepository repository;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    	
    	return http
        		.csrf().disable()
            	.cors().disable()
            	.authorizeHttpRequests()
            	.antMatchers("/login").permitAll()
            	.anyRequest()
            	.authenticated()
            	.and()
            	.sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            	.and()
            	.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            	.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new UserDetailsService() {
    		@Override
            public User loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findOneByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found"));
            }
    	};
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
     
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

}
