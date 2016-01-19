package uz.sherzodn.web.handler.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import uz.sherzodn.web.dto.SystemUser;
import uz.sherzodn.web.dto.UserAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sherzod Nurjonov
 */
public class TokenAuthenticationHandler {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private Integer sessionTimeoutMinutes;

    private TokenHandler tokenHandler;

    public TokenAuthenticationHandler(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
        if (tokenHandler != null) {
            this.sessionTimeoutMinutes = tokenHandler.sessionTimeoutMinutes();
        }
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final SystemUser user = (SystemUser) authentication.getDetails();
        if (sessionTimeoutMinutes != null) {
            user.setExpires(System.currentTimeMillis() + (1000 * 60 * sessionTimeoutMinutes));
        }
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createToken(user));
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final SystemUser user = tokenHandler.getUser(token);
            if (user != null) {
                UserAuthentication userAuthentication = new UserAuthentication(user);
                //update expires time
                if (sessionTimeoutMinutes != null) {
                    addAuthentication(response, userAuthentication);
                }
                return userAuthentication;
            }
        }
        return null;
    }

    public void removeAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            tokenHandler.removeUser(token);
        }

    }
}
