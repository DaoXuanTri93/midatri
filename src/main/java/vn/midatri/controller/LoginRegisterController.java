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

    @GetMapping()
    public ModelAndView login(){

        return new ModelAndView("/admin/login-register");
    }
}
