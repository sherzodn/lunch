package uz.sherzodn.web.dto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Sherzod Nurjonov
 */
public class UserAuthentication implements Authentication{
    private final SystemUser user;
    private boolean authenticated = true;

    public UserAuthentication(SystemUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public SystemUser getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated){
        this.authenticated=authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
