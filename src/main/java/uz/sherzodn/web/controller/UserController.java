package uz.sherzodn.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.sherzodn.model.User;
import uz.sherzodn.service.UserManager;
import uz.sherzodn.web.dto.SystemUser;
import uz.sherzodn.web.dto.UserAuthentication;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserManager userManager;


    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public SystemUser getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }
        return null;
    }

    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userManager.saveUser(user);
    }


    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userManager.getAll();
    }


    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public User getRestaurantById(@PathVariable Long id) {
        return userManager.get(id);
    }


}
