package vn.midatri.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @GetMapping("")
    public ModelAndView showReport(){

        return new ModelAndView("/admin/report/date");

    }
    @GetMapping("/goods")
    public ModelAndView showReportGoods(){

        return new ModelAndView("/admin/report/goods");

    }

}
