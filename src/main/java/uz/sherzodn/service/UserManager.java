package uz.sherzodn.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.sherzodn.model.User;
import uz.sherzodn.web.dto.SystemUser;

/**
 * Created by Sherzod Nurjonov
 */
public interface UserManager extends GenericManager<User, Long>{

    User saveUser(User user);

}
