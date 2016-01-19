package uz.sherzodn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.sherzodn.dao.RestaurantDao;
import uz.sherzodn.model.Restaurant;
import uz.sherzodn.service.RestaurantManager;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@Service("restaurantManager")
public class RestaurantManagerImpl extends GenericManagerImpl<Restaurant, Long> implements RestaurantManager {

    private RestaurantDao restaurantDao;

    @Autowired
    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.dao = restaurantDao;
        this.restaurantDao = restaurantDao;
    }


}
