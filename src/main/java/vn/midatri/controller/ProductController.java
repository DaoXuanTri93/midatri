package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public ModelAndView ProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/list");
        return modelAndView;
    }


    @GetMapping("/restore")
    public  ModelAndView restoreProduct(){
        return new ModelAndView("/admin/product/restore");
    }

}
