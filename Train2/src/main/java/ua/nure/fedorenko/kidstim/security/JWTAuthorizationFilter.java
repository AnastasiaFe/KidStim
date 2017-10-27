package ua.nure.fedorenko.kidstim.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.nure.fedorenko.kidstim.service.dto.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = Logger.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        LOGGER.info("Authorization filter is working...Attempt of authentication");
        UserDTO creds = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
        System.out.println(creds.getPassword());
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getEmail(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        LOGGER.info("Authentication is successful");
        res.addHeader("User", auth.getName() + ";" + auth.getAuthorities().iterator().next().toString());
        System.out.println(res.getHeader("User"));
        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }
}