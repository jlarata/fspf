package com.portfolio.fspf.security.JWT;

import com.portfolio.fspf.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Obtiene el token y el usuario (a partir del nombre de usuario) y comprueba si la autenticación es correcta.
// en caso afirmativo la añade al contexto y, por último, se añade al filtro.

// el token tiene la forma de la cadena Bearer xxxxx.yyyyy.zzzz. con getToken() se elimina el inicio <<Bearer<<


public class JwtTokenFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if(token !=null && jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("fail en el método doFilter " + e.getMessage());
        }
        filterChain.doFilter(req, res);
        
    }
    
    private String getToken(HttpServletRequest request){
        String authReq = request.getHeader("Authorization");
        if(authReq != null && authReq.startsWith("Bearer "))
            return authReq.replace("Bearer ", "");
        return null;
    }
    
}
