//package vn.midatri.controller;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class SecurityController {
//
//
//    private String getPrincipal() {
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails) principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }
//
//    @GetMapping(value = {"/", "/home"})
//    public String Homepage(Model model) {
//        model.addAttribute("user", getPrincipal());
//        return "/order/listOrder";
//    }
//    @GetMapping("/accessDenied")
//    public String accessDenied(Model model) {
//        model.addAttribute("user", getPrincipal());
//        return "/404/404";
//    }
//
//}
