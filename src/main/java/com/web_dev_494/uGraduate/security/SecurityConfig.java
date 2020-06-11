package com.web_dev_494.uGraduate.security;

// Security Configuration class. Updates roles and assigns security to classpaths
import com.web_dev_494.uGraduate.repo.UserDetailsServiceImpl;
import com.web_dev_494.uGraduate.security.jwt.JwtUsernameAndPasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
// Order here is important. In order to have multiple mvcmatchers on different map heirarchies
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // this is needed for bcrypt hashing
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    // TODO: Fix xss. vulnerable if using /advisor/XSSEXAMPLE
    // Configures roles on endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // this first line is making sure all /admin/ mappings can only be used by "ADMIN" users
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
