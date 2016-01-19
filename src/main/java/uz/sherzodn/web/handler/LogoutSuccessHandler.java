package uz.sherzodn.web.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import uz.sherzodn.web.handler.token.TokenAuthenticationHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sherzod Nurjonov
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Autowired
    TokenAuthenticationHandler tokenAuthenticationHandler;

    // Just for setting the default target URL
    public LogoutSuccessHandler(String defaultTargetURL) {
        this.setDefaultTargetUrl(defaultTargetURL);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // remove user from token session store
        tokenAuthenticationHandler.removeAuthentication(request);
        // do whatever you want
        super.onLogoutSuccess(request, response, authentication);
    }
}
