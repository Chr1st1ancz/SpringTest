package cz.spsmb.controller;

import cz.spsmb.entity.Item;
import cz.spsmb.entity.User;
import cz.spsmb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserContoller {

    private UserService userService;

    @Autowired
    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/userlogin", method = RequestMethod.POST)
    public void userLogin(@RequestParam String name, @RequestParam String password) {
        System.out.println(this.userService.isAuth(name, password));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void saveItem(@RequestBody User user) { userService.save(user);
    }
}
