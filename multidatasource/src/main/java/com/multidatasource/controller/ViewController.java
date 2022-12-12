package com.multidatasource.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/view")
public class ViewController {

    @GetMapping(value = "/view")
    public ModelAndView view() {
        ModelAndView view = new ModelAndView();
        view.setViewName("view");
        return view;
    }

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

}
