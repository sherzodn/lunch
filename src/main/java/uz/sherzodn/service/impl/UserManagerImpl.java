package uz.sherzodn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sherzodn.dao.UserDao;
import uz.sherzodn.model.Role;
import uz.sherzodn.model.User;
import uz.sherzodn.service.UserManager;
import uz.sherzodn.web.handler.SystemUser;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sherzod Nurjonov
 */
@Service("userManager")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User saveUser(final User user) {
        User oldUser = null;
        if (passwordEncoder != null) {
            if (user.getId() == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                oldUser = userDao.get(user.getId());
                user.setPassword(oldUser.getPassword());
            }
        } else {
            logger.warn("PasswordEncoder not set, skipping password encryption...");
        }
        try {
            return userDao.saveUser(user);
        } catch (final Exception e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public SystemUser loadUserByUsername(final String username) {
        User user = userDao.loadUserByUsername(username);
        Set<Role> roles = new HashSet<>();
        roles.add(user.getRole());
        SystemUser systemUser = new SystemUser(user.getId(), user.getFullName(), user.getUsername(), user.getPassword(), roles);
        return systemUser;
    }
}
