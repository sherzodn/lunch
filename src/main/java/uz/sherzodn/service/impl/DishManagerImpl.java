package uz.sherzodn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.sherzodn.dao.DishDao;
import uz.sherzodn.model.Dish;
import uz.sherzodn.service.DishManager;

/**
 * Created by Sherzod Nurjonov
 */
@Service("customerManager")
public class DishManagerImpl extends GenericManagerImpl<Dish, Long> implements DishManager {

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dao = dishDao;
    }
}
