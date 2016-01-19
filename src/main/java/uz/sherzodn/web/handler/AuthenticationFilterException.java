package uz.sherzodn.web.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Sherzod Nurjonov
 */
public class AuthenticationFilterException extends AuthenticationException{
    String code;

    public AuthenticationFilterException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }

    public AuthenticationFilterException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
