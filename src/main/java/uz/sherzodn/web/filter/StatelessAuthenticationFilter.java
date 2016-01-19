package uz.sherzodn.web.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import uz.sherzodn.web.handler.token.TokenAuthenticationHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sherzod Nurjonov
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {
    private final TokenAuthenticationHandler tokenAuthenticationHandler;


    protected StatelessAuthenticationFilter(TokenAuthenticationHandler taService) {
        this.tokenAuthenticationHandler = taService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        Authentication authentication = tokenAuthenticationHandler.getAuthentication((HttpServletRequest) req, (HttpServletResponse) res);

        // set current authenticated user to security context (since REST is stateless, we have to do this for every request)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res); // always continue
    }
}
