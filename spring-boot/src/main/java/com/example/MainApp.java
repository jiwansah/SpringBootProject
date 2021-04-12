package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@SpringBootApplication
@ComponentScan
public class MainApp {
	
    public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
   
    
    @EnableWebSecurity
	@Configuration	
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				//.antMatchers(HttpMethod.GET, "/api/services/**").hasAnyRole("ADMIN", "USER")
				//.antMatchers(HttpMethod.GET, "/api/services/controller/user").hasAuthority("ROLE_ADMIN") //ROLE_ADMIN
				.antMatchers(HttpMethod.POST, "/api/services/controller/user/login").permitAll()
				//.antMatchers(HttpMethod.GET, "/api/services/controller/user").hasAnyRole("USER")
				.anyRequest().authenticated();
		}
	}

    
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder(); 
//    }
    
}