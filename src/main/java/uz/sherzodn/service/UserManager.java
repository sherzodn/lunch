package uz.sherzodn.service;

import uz.sherzodn.model.User;

/**
 * Created by Sherzod Nurjonov
 */
public interface UserManager extends GenericManager<User, Long>{

    User saveUser(User user);

}
