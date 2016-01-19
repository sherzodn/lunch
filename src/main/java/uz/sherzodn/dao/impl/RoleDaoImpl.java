package uz.sherzodn.dao.impl;

import org.springframework.stereotype.Repository;
import uz.sherzodn.dao.RoleDao;
import uz.sherzodn.model.Role;

/**
 * Created by Sherzod Nurjonov
 */
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public RoleDaoImpl() {
        super(Role.class);
    }
}
