package uz.sherzodn.web.handler.token;

import uz.sherzodn.web.handler.SystemUser;

/**
 * Created by Sherzod Nurjonov
 */
public interface TokenHandler {
    public SystemUser getUser(String token);

    public String createToken(SystemUser user);

    public void removeUser(String token);

    public Integer sessionTimeoutMinutes();
}
