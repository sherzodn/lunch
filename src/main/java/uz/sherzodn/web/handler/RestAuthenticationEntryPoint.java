package uz.sherzodn.web.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This entry point is called once the request missing the authentication but if
 * the request doesn't have the cookie then we send the unauthorized response.
 *
 * Created by Sherzod Nurjonov
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{


    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
                         AuthenticationException arg2) throws IOException, ServletException {
            arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
