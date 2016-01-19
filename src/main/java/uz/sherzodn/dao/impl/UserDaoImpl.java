package uz.sherzodn.dao.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import uz.sherzodn.dao.UserDao;
import uz.sherzodn.model.User;

import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (User) users.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    @Override
    public User saveUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("user's id: " + user.getId());
        }
        getSession().merge(user);
        getSession().flush();
        return user;
    }

    @Override
    public User save(User user) {
        return this.saveUser(user);
    }
}
