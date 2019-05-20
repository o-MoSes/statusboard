package rocks.monsees.statusboard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import rocks.monsees.statusboard.service.EmployeeDetailsService;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	//csrf and headers disabled to access h2 console
        	.csrf().disable()
        	.headers().frameOptions().disable().and()
            .authorizeRequests()
                .antMatchers("/","/h2/**").permitAll()
                .antMatchers("/resources/**","/webjars/**","/css/**","/js/**","/images/**").permitAll() 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/authenticate") //triggers the login -> action="/authenticate"
                .defaultSuccessUrl("/dashboard")
                .and()
            .logout()
            	.logoutSuccessUrl("/?logout")
            	.logoutUrl("/checkout") //triggers the logout -> action="/checkout"
            	.invalidateHttpSession(true)
                .permitAll();
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new EmployeeDetailsService();
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    
    
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("1")
//                .password("1")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}