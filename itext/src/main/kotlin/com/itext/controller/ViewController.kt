package com.itext.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.ModelAndView

@RestControllerAdvice
@RequestMapping(value = ["/view"])
class ViewController {
    @GetMapping(value = ["/index"])
    fun view(): ModelAndView? {
        val view = ModelAndView()
        view.viewName = "index"
        return view
    }
}