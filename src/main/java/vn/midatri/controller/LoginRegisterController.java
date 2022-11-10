package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginRegisterController {

    @GetMapping()
    public ModelAndView Login(){
        return new ModelAndView("/admin/login-register");
    }
//    @GetMapping("/admin")
//    public ModelAndView admin(){
//        return new ModelAndView("/products");
//    }
//    @GetMapping("/user")
//    public ModelAndView user(){
//        return new ModelAndView("/orders");
//    }


}
