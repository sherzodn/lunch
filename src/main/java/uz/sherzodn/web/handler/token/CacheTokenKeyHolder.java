package uz.sherzodn.web.handler.token;

import java.io.Serializable;

/**
 * Created by Sherzod Nurjonov
 */
public class CacheTokenKeyHolder implements Serializable{
    private String salt1, salt2;
    private String cacheKey;
    private String token;

    public CacheTokenKeyHolder(String salt1, String salt2, String cacheKey, String token) {
        this.salt1 = salt1;
        this.salt2 = salt2;
        this.cacheKey = cacheKey;
        this.token = token;
    }

    public String getSalt1() {
        return salt1;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt2() {
        return salt2;
    }
}
