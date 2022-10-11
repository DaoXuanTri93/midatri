package vn.midatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
@Autowired


    @GetMapping({"", "/"})
    public ModelAndView UserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/listUser");
        return modelAndView;
    }

    @GetMapping()
    public ModelAndView showAllUser(){
        ModelAndView modelAndView = new ModelAndView();


        return null;
    }
}
