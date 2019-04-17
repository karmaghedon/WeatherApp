/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api.model;

/**
 *
 * @author nicolaenastas
 */
public class CityModel {
 
    private String cityName;
    private String copuntry;
    private String latetiude;
    private String longetude;

    public CityModel(String cityName, String copuntry, String latetiude,String longetude) {
        this.cityName = cityName;
        this.copuntry = copuntry;
        this.latetiude = latetiude;
        this.longetude = longetude;
    }
    public CityModel(String cityName, String copuntry) {
        this.cityName = cityName;
        this.copuntry = copuntry;
    }
   
    
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCopuntry() {
        return copuntry;
    }

    public void setCopuntry(String Copuntry) {
        this.copuntry = Copuntry;
    }

    public String getLatetiude() {
        return latetiude;
    }

    public void setLatetiude(String latetiude) {
        this.latetiude = latetiude;
    }

    public String getLongetude() {
        return longetude;
    }

    public void setLongetude(String longetude) {
        this.longetude = longetude;
    }
    
    
    
}
