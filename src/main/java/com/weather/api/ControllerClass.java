/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nicolaenastas
 */
@Controller
@RequestMapping("/weather")
public class ControllerClass {

    @GetMapping("/")
    public ModelAndView landingPage() {

        ModelAndView model = new ModelAndView();

        model.setViewName("homePage");

        return model;
    }

    @GetMapping("/list/{city},{country}")
    public ModelAndView listAll(@PathVariable String city, @PathVariable String country) {
        ModelAndView model = new ModelAndView();
        model.addObject("listAll", city);
        model.addObject("listAll", country);
        model.setViewName("listAll");

        return model;
    }

        @GetMapping("search/{city}")
        public ModelAndView getByName(@PathVariable String city) {
        ModelAndView model = new ModelAndView();
        model.addObject("search", city);
        model.setViewName("search");
        
            return model;
    }
}
