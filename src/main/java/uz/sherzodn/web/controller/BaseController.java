package uz.sherzodn.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.sherzodn.web.handler.SystemUser;

import java.nio.charset.Charset;

/**
 * Created by Sherzod Nurjonov
 */
public abstract class BaseController {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    public static final String APPLICATION_JSON_UTF8_VALUE = APPLICATION_JSON_UTF8.toString();
    public static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected SystemUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof SystemUser) {
            return (SystemUser) authentication.getDetails();
        }
        return null;
    }
}
