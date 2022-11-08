package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping({"/home", "/", ""})
public class HomeController {
    @GetMapping()
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("/admin/home1");
    }

//    @GetMapping("/dashboard1")
//    public ModelAndView dashboard1() {
//        ModelAndView m = new ModelAndView("/admin/test");
//        m.addObject("taotest", Arrays.asList(new int[]{400, 430, 448, 470}).toArray());
//        return m;
//    }
}
