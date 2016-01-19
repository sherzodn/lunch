package uz.sherzodn.dao.impl;

import org.springframework.stereotype.Repository;
import uz.sherzodn.dao.DishDao;
import uz.sherzodn.model.Dish;

/**
 * Created by Sherzod Nurjonov
 */
@Repository("dishDao")
public class DishDaoImpl extends GenericDaoImpl<Dish, Long> implements DishDao {

    /**
     * Constructor to create a Generics-based version using Dish as the entity
     */
    public DishDaoImpl() {
        super(Dish.class);
    }
}
