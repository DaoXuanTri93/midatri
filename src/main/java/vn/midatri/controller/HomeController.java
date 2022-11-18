package vn.midatri.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.dto.TUserDTO;
import vn.midatri.dto.TUserDTO1;
import vn.midatri.repository.UserRepository;
import vn.midatri.service.IUserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping({"/home", "/", ""})
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping()
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("/admin/home1");
    }


    @GetMapping("/userDTO")
    public ResponseEntity<?> findAllUserDTO(){
        List<TUserDTO1> userDTOS = userRepository.getAllTUserDTO1();
        for (TUserDTO1 i : userDTOS) {
            System.out.println("Phone: " + i.getPHONE() + " Email: " + i.getFULL_NAME());
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

//    @GetMapping("/dashboard1")
//    public ModelAndView dashboard1() {
//        ModelAndView m = new ModelAndView("/admin/test");
//        m.addObject("taotest", Arrays.asList(new int[]{400, 430, 448, 470}).toArray());
//        return m;
//    }
}
