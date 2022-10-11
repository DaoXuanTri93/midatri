package vn.midatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.UserRepository;
import vn.midatri.repository.model.User;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
    UserRepository userRepository;

    @GetMapping({"", "/"})
    public ModelAndView UserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/listUser");
        return modelAndView;
    }
}
