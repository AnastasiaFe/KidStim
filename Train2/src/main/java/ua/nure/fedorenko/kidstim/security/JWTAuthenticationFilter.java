package ua.nure.fedorenko.kidstim.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JWTAuthenticationFilter extends GenericFilterBean {

    private static final Logger LOGGER = Logger.getLogger(JWTAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.info("Authentication filter is working...");
        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) request);
        if (authentication != null) {
            LOGGER.info("Is authenticated:" + authentication.isAuthenticated());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}