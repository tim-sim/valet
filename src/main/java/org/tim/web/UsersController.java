package org.tim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tim.domain.User;
import org.tim.service.UsersService;

/**
 * @author tim
 */
@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String entry() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("secondName") String secondName,
            @RequestParam("email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        usersService.createUser(user);

        return "redirect:/";
    }

}
