package vn.midatri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.midatri.repository.TableTopRepository;

@Controller
@RequestMapping("/table-top")
public class TableTopController {
    @Autowired
    TableTopRepository tableTopRepository;

    @GetMapping()
    public ModelAndView TableTopPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/table/listTable");
        return modelAndView;
    }
    @GetMapping("/restoreTable")
    public ModelAndView TalbeRestore(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/table/restoreTable");
        return modelAndView;
    }
}
