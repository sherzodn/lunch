package uz.sherzodn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uz.sherzodn.model.Restaurant;
import uz.sherzodn.service.RestaurantManager;

import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantManager restaurantManager;

    /**
     * Create or update Restaurant with it's Dishes
     *
     * @param restaurant
     * @return saved/updated restaurant object
     */
    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantManager.save(restaurant);
    }

    /**
     * Get list of all restaurants
     *
     * @return List
     */
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return restaurantManager.getAll();
    }

    /**
     * Get restaurant by restaurant id
     *
     * @param id
     * @return restaurant
     */
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantManager.get(id);
    }


}
