/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api.methods;

/**
 *
 * @author nicolaenastas
 */
public interface ProcessJsonInterface {

    String jSonParseList(String cityName, String country);

    String jSonParse(String cityName);

    String jSonParseMap(String layer, String zoom, String lat, String lon);
    
}
