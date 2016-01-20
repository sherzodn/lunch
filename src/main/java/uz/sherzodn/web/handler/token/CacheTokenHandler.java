package uz.sherzodn.web.handler.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import uz.sherzodn.web.handler.SystemUser;

import javax.xml.bind.DatatypeConverter;
import java.util.UUID;

/**
 * Created by Sherzod Nurjonov
 */
public class CacheTokenHandler implements TokenHandler {
    public static final String SEPARATOR = "H38";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private boolean onlyOneSession = true;

    @Autowired
    CacheManager cacheManager;

    public Cache getCache() {
        return cacheManager.getCache("tokensCache");
    }

    public String createToken(SystemUser user) {
        Assert.notNull(user);
        Assert.notNull(user.getId());
        CacheTokenKeyHolder cacheTokenKeyHolder = getTokenHolderFromId(user.getId().toString());
        if (cacheTokenKeyHolder != null) {
            user.setCurrentSessionToken(cacheTokenKeyHolder.getToken());//setting current token for user
            getCache().put(cacheTokenKeyHolder.getCacheKey(), user);
            return cacheTokenKeyHolder.getToken();
        } else {
            return null;
        }
    }

    public SystemUser getUser(String token) {
        CacheTokenKeyHolder cacheTokenKeyHolder = getTokenHolderFromToken(token);
        if (cacheTokenKeyHolder != null) {
            Cache.ValueWrapper valueWrapper = getCache().get(cacheTokenKeyHolder.getCacheKey());
            if (valueWrapper != null) {
                SystemUser systemUser = (SystemUser) valueWrapper.get();
                if (onlyOneSession && !token.equals(systemUser.getCurrentSessionToken())) {
                    logger.warn("Token {} not match with current token {} of user {} ", token, systemUser.getCurrentSessionToken(), systemUser.getUsername());
                } else {
                    return (SystemUser) valueWrapper.get();
                }
            }
        }
        return null;
    }

    public void setOnlyOneSession(boolean onlyOneSession) {
        this.onlyOneSession = onlyOneSession;
    }

    @Override
    public void removeUser(String token) {
        CacheTokenKeyHolder cacheTokenKeyHolder = getTokenHolderFromToken(token);
        getCache().evict(cacheTokenKeyHolder.getCacheKey());
    }

    @Override
    public Integer sessionTimeoutMinutes() {
        return null;//timeout controlled in ehcache.xml for this handler
    }

    private String beatifyUid(String uid) {
        return uid.toUpperCase().replace('-', '1');
    }

    public CacheTokenKeyHolder getTokenHolderFromId(String id) {
        String salt1 = beatifyUid(UUID.randomUUID().toString());//beatifying salt1
        String salt2 = beatifyUid(UUID.randomUUID().toString());//beatifying salt2
        String rawToken = salt1 + SEPARATOR + id + SEPARATOR + salt2;
        String token = DatatypeConverter.printBase64Binary(rawToken.getBytes());

        return new CacheTokenKeyHolder(salt1, salt2, id, token);
    }

    public CacheTokenKeyHolder getTokenHolderFromToken(String token) {

        String rawToken = new String(DatatypeConverter.parseBase64Binary(token));
        String[] tokens = rawToken.split(SEPARATOR);
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Wrong token provided");
        }

        return new CacheTokenKeyHolder(tokens[0], tokens[2], tokens[1], token);
    }
}
