package uz.sherzodn.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import uz.sherzodn.model.User;

import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Gets users information based on login name.
     */
    @Transactional
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Gets a list of users ordered by the uppercase version of their username.
     *
     * @return List populated list of users
     */
    List<User> getUsers();

    /**
     * Saves a user's information.
     * @param user the object to be saved
     * @return the persisted User object
     */
    User saveUser(User user);
}
