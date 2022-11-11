package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/home", "/", ""})
public class HomeController {
    @GetMapping()
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("/admin/home");
    }


}
