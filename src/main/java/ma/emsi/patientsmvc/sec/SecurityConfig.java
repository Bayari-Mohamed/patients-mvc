package ma.emsi.patientsmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.PrintStream;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   String encodedPWD = passwordEncoder.encode("1234");


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(encodedPWD);
       auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER");
       auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("2345")).roles("ADMIN","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/user/**" ).hasRole("USER");
       // http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

@Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
}
}
