package uz.sherzodn.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uz.sherzodn.dao.RestaurantDao;
import uz.sherzodn.model.Restaurant;

import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@Repository("restaurantDao")
public class RestaurantDaoImpl extends GenericDaoImpl<Restaurant, Long> implements RestaurantDao {

    /**
     * Constructor to create a Generics-based version using Restaurant as the entity
     */
    public RestaurantDaoImpl() {
        super(Restaurant.class);
    }


}
