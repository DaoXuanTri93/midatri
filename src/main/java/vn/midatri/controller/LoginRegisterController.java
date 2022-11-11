package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.dto.user.UserLogin;

@Controller
@RequestMapping("/login")
public class LoginRegisterController {

    @GetMapping()
    public ModelAndView Login(){
        return new ModelAndView("/admin/login-register");
    }


    @PostMapping()
    public String loginUser(@ModelAttribute UserLogin userLogin){
        System.out.println(userLogin);
        return "redirect:/orders";
    }

}
