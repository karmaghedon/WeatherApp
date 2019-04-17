/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author nicolaenastas
 */
public class WeatherDataModel {

    public String dateStamp;
    public String temp;
    public String minTemp;
    public String maxTemp;
    public String precipitations;
    public String icon;
    public String clouds;
    public String windSpeed;
    public String cityName;
    public String country;
    public String lat;
    public String lon;

    public WeatherDataModel(String dateStamp, String temp, String min, String max,
            String precipitations, String icon, String clouds, String windSpeed, String cityName, String country) throws ParseException {
        this.dateStamp = getTimeString(dateStamp);
        this.temp = roundDouble(temp);
        this.minTemp = roundDouble(min);
        this.maxTemp = roundDouble(max);
        this.precipitations = precipitations;
        this.icon = icon;
        this.clouds = clouds + "%";
        this.windSpeed = roundDouble(windSpeed) + " meter/sec";
        this.cityName = cityName.substring(1, cityName.length() - 1);
        this.country = country.substring(1, country.length() - 1);
    }

    public WeatherDataModel(String dateStamp, String temp, String min, String max,
            String precipitations, String icon, String clouds, String windSpeed, String cityName, String country, String lat, String lon) throws ParseException {
        this.dateStamp = getTimeString(dateStamp);
        this.temp = roundDouble(temp);
        this.minTemp = roundDouble(min);
        this.maxTemp = roundDouble(max);
        this.precipitations = precipitations;
        this.icon = icon;
        this.clouds = clouds + "%";
        this.windSpeed = roundDouble(windSpeed) + " meter/sec";
        this.cityName = cityName.substring(1, cityName.length() - 1);
        this.country = country.substring(1, country.length() - 1);
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Time:" + dateStamp + "Temp=" + temp + " Min:" + minTemp + " Max:" + maxTemp;
    }

    private String getTimeString(String jSonDate) throws ParseException {
//	String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";	
////	public static final String NEW_DATE_PARSER = "EEE MMM d HH:mm:ss zzz yyyy";
//     SimpleDateFormat jSonDateFormat = new SimpleDateFormat(JSON_DATE_FORMAT);
//        Instant instant = Instant.ofEpochSecond(Long.parseLong(jSonDate));
//        OffsetDateTime odt = OffsetDateTime.parse(instant.toString());
//        OffsetDateTime odtTruncatedToWholeSecond = odt.truncatedTo(ChronoUnit.SECONDS);
//        String output = odtTruncatedToWholeSecond.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", " ");
//        Date tokenExpirationDateFormated = jSonDateFormat.parse(output);
//        //ZoneId zoneToronto = ZoneId.of("Americas/Montreal");
//        //ZonedDateTime zdtToronto = instant.atZone(zoneToronto);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
//        return sdf.format(tokenExpirationDateFormated);
//        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date date = new Date(Long.parseLong(jSonDate)*1000);
        return sdf.format(date);
    }
    
    private String roundDouble(String number){
        Double tempNumber = Double.parseDouble(number);
        String result = "";
        return result.valueOf(Math.round(tempNumber));
    }
}
