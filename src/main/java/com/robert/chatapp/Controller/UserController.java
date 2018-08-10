package com.robert.chatapp.Controller;

import com.robert.chatapp.Dao.UserDAO;
import com.robert.chatapp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/list")
    public String listCustomers(Model model) {

        List<User> users = userDAO.getUsers();
        users.remove(0);

        model.addAttribute("users", users);

        return "list-users";
    }
}
