package com.robert.chatapp.Controller;

import com.robert.chatapp.Dao.UserDAOImpl;
import com.robert.chatapp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAOImpl userDAO;

    @Autowired
    public UserController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/list")
    public String listCustomers(Model model) {

        List<User> users = userDAO.getUsers();

        model.addAttribute("users", users);

        return "list-users";
    }
}
