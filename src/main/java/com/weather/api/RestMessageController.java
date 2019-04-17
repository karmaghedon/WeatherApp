/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api;

import com.weather.api.methods.ProcessJsonImpl;
import com.weather.api.methods.ProcessJsonInterface;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nicolaenastas
 */
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestMessageController {

    private ProcessJsonInterface processJson;

    public ProcessJsonInterface getProcessJson() {
        return processJson;
    }

    @Autowired
    public void setProcessJson(ProcessJsonInterface processJson) {
        this.processJson = processJson;
    }

    @RequestMapping(value = "/getAll/{city},{country}", method = RequestMethod.GET)
    public String getJsonList(@PathVariable String city, @PathVariable String country) {

        String response = processJson.jSonParseList(city, country);
        return response;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getJson() {

        String response = processJson.jSonParse("Toronto");
        return response;
    }

    @RequestMapping(value = "/getName/{city}", method = RequestMethod.GET)
    public String getJsonByName(@PathVariable String city) {

        String response = processJson.jSonParse(city);
        return response;
    }

    @RequestMapping(value = "/getMap/{layer},{zoom},{lat},{lon}", method = RequestMethod.GET)
    public String getMap(@PathVariable String layer, @PathVariable String zoom,
            @PathVariable String lat, @PathVariable String lon) {

        String response = processJson.jSonParseMap(layer, zoom, lat, lon);
        return response;
    }

}
