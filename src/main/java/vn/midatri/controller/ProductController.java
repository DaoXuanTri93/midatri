package vn.midatri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.Item;
import vn.midatri.repository.model.User;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("")
    public ModelAndView UserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/list");
        return modelAndView;
    }
//    @GetMapping
//    public ModelAndView showListPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("product/list");
//        List<Item> products = ;
//        List<Category> categories = ;
//        User user = ;
//        modelAndView.addObject("user", user);
//        modelAndView.addObject("categories", categories);
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

}
