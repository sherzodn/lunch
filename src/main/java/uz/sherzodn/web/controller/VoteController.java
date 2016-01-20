package uz.sherzodn.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sherzodn.model.Restaurant;
import uz.sherzodn.model.User;
import uz.sherzodn.model.Vote;
import uz.sherzodn.service.RestaurantManager;
import uz.sherzodn.service.UserManager;
import uz.sherzodn.service.VoteManager;
import uz.sherzodn.utils.DateUtils;
import uz.sherzodn.web.handler.SystemUser;

import java.util.Date;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@RestController
@RequestMapping("/vote")
public class VoteController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(VoteController.class);
    @Autowired
    private RestaurantManager restaurantManager;
    @Autowired
    private VoteManager voteManager;
    @Autowired
    private UserManager userManager;

    public final int hourAM = 11;//todo get this value from property

   /* @Value("${change.mind.hour}")
    public void setHourAM(String hourAM) {
        this.hourAM = hourAM;
    }*/

    /**
     * Voting restaurant by current user
     *
     * @param restaurantId restaurant id which wanted to vote
     * @return ResponseEntity
     */
    @RequestMapping(value = "/get/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> voteRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantManager.get(restaurantId);
        SystemUser systemUser = getCurrentUser();
        User currentUser = userManager.get(systemUser.getId());
        Date now = new Date();
        Vote vote = voteManager.getVoteByParams(currentUser.getId(), now);
        if (vote == null) {
            vote = voteManager.save(new Vote(currentUser, restaurant, now));
            logger.info("Created new vote for {} restaurant by {} user at {}", restaurant.getName(), currentUser.getUsername(), now);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        } else if (DateUtils.isDateAfterAnyAM(now, hourAM)) {
            logger.info("User {} attempted to change his mind to {} restaurant  at {}, but it was late", currentUser.getUsername(), restaurant.getName(), now);
            return new ResponseEntity<>(vote, HttpStatus.NOT_MODIFIED);
        } else {
            String oldRestaurant = vote.getRestaurant().getName();
            vote.setRestaurant(restaurant);
            vote.setVotedDate(now);
            vote = voteManager.save(vote);
            logger.info("User {} changed his mind from {} to {} restaurant at {}", currentUser.getUsername(), oldRestaurant, restaurant.getName(), now);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/today-list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Vote> getTodayVotesByRestaurant() {
        return voteManager.getVotesToday();
    }

}
