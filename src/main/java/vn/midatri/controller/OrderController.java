package vn.midatri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping()
    public ModelAndView UserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/order/listOrder");
        return modelAndView;
    }
    @GetMapping("/bill")
    public ModelAndView bills(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bills/bills");
        return modelAndView;
    }
}
