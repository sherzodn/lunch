package uz.sherzodn.web.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.sherzodn.model.Role;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Sherzod Nurjonov
 */
public class SystemUser implements UserDetails {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private Set<Role> roles;
    //system fields
    private String currentSessionToken;
    private Long expires;

    public SystemUser() {
    }

    public SystemUser(Long id, String fullName, String username, String password, Set<Role> roles) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public String getCurrentSessionToken() {
        return currentSessionToken;
    }

    @JsonIgnore
    public void setCurrentSessionToken(String currentSessionToken) {
        this.currentSessionToken = currentSessionToken;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
