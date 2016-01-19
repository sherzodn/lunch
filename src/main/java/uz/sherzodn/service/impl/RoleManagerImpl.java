package uz.sherzodn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.sherzodn.dao.RoleDao;
import uz.sherzodn.model.Role;
import uz.sherzodn.service.RoleManager;

/**
 * Created by Sherzod Nurjonov
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements RoleManager {

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.dao = roleDao;
    }
}
