package uz.sherzodn.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uz.sherzodn.web.handler.SystemUser;
import uz.sherzodn.web.handler.UserAuthentication;
import uz.sherzodn.web.handler.AuthenticationFilterException;
import uz.sherzodn.web.handler.token.TokenAuthenticationHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sherzod Nurjonov
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
    public static final String AUTH_ERR_CODE = "AUTH_ERR_CODE";
    public static final String AUTH_ERR_MSG = "AUTH_ERR_MSG";

    private final TokenAuthenticationHandler tokenAuthenticationHandler;
    private final UserDetailsService userDetailsService;

    protected StatelessLoginFilter(String urlMapping, TokenAuthenticationHandler tokenAuthenticationHandler,
                                   UserDetailsService userDetailsService, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping));
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationHandler = tokenAuthenticationHandler;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        //Get user credentials from request
        final SystemUser user = getSystemUser(request);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final SystemUser authenticatedUser = (SystemUser) userDetailsService.loadUserByUsername(authentication.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
        authenticatedUser.setPassword(null);//clear password to prevent holding it in token
        // Add the custom token as HTTP header to the response
        tokenAuthenticationHandler.addAuthentication(response, userAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String errorCode = failed.getClass().getSimpleName();
        if (failed instanceof AuthenticationFilterException) {
            errorCode = ((AuthenticationFilterException) failed).getCode();
        }
        response.setHeader(AUTH_ERR_CODE, errorCode); //error code could be one of implementations of AuthenticationException classes (there are big amount of them) or AuthenticationFilterException with code which listed below getSystemUser description
        response.setHeader(AUTH_ERR_MSG, failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);
    }


    private SystemUser getSystemUser(HttpServletRequest request) throws AuthenticationFilterException {
        SystemUser user;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), SystemUser.class);
        } catch (Exception e) {
            throw new AuthenticationFilterException(e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return user;
    }


}
