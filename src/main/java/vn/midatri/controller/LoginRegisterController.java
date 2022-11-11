package vn.midatri.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.dto.user.UserLogin;
import vn.midatri.repository.UserRepository;
import vn.midatri.repository.model.User;

@Controller
@RequestMapping("/login")
public class LoginRegisterController {

    @Autowired
    private UserRepository userRepository;
//    @GetMapping()
//    public ModelAndView Login(){
//
//        return new ModelAndView("/admin/login-register");
//    }


    @GetMapping()
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "/admin/login-register";
    }

    @PostMapping()
    public ModelAndView loginUser(@ModelAttribute User user){
        User userLogin = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/orders");
        userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
