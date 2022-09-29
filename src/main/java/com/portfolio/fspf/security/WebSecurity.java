package com.portfolio.fspf.security;

import com.portfolio.fspf.security.JWT.JwtEntryPoint;
import com.portfolio.fspf.security.JWT.JwtTokenFilter;
import com.portfolio.fspf.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//tres anotaciones: la última sirve para que en los métodos del controlador se indique qué privilegios
//son necesarios para acceder a sus recursos.

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
  
    //dos inyecciones de dependencias con autowired
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    //dos bean: el passwordencoder que se usa para cifrar contraseñas y un jwttokenfilter
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    //en el siguiente método utilizamos el objeto userDetailsServiceImpl y ciramos la contraseña
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
    
    //configuramos las opciones de seguridad del proyecto.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //el método permite a todo el mundo acceder a este path
                .antMatchers("/api/auth/**").permitAll()
                //pero para el resto de recursos hay que estar autenticado
                .anyRequest().authenticated()
                .and()
                //se utiliza el jwtentrypoint
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                //se añade el filtro
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                
                
                
                
                
                
                
                
                
                
                
    }
    
}
