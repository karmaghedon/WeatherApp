/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weather.api.methods;


import com.google.gson.*;
import com.weather.api.constants.Constants;
import com.weather.api.model.WeatherDataModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author nicolaenastas
 */
@Component
public class ProcessJsonImpl implements ProcessJsonInterface {

    @Override
    public String jSonParseList(String cityName, String country) {

        String url = Constants.HTTP_ADDRESS_LIST
                + ("q=" + cityName + ",")
                + country
                + Constants.API_KEY;

        Response response = getResponce(url);

        if (response.getStatus() == 200) {
            String json = response.readEntity(String.class);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(json);
            List<WeatherDataModel> items = new ArrayList<>();

            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();

                JsonArray forecastList = (JsonArray) jsonObject.get("list");

                for (int i = 0; i < forecastList.size(); i++) {

                    JsonObject o = (JsonObject) forecastList.get(i);
                    String dt = o.get("dt").toString();
                    JsonObject main = o.getAsJsonObject("main");
                    String temp = main.get("temp").toString();
                    String mintemp = main.get("temp_min").toString();
                    String maxtemp = main.get("temp_max").toString();
                    JsonArray weatherList = (JsonArray) o.getAsJsonArray("weather");
                    String precipitations = "";
                    String icon = "";
                    for (int j = 0; j < weatherList.size(); j++) {
                        JsonObject weather = (JsonObject) weatherList.get(0);
                        precipitations = weather.get("description").toString();
                        icon = Constants.IMAGE_ICON + weather.get("icon").toString().substring(1, weather.get("icon").toString().length() - 1) + ".png";
                    }

                    JsonObject clouds = o.getAsJsonObject("clouds");
                    String cloudsPercentage = clouds.get("all").toString();
                    JsonObject wind = o.getAsJsonObject("wind");
                    String windSpeed = wind.get("speed").toString();
                    JsonObject city = jsonObject.getAsJsonObject("city");
                    String cityNamefromJson = city.get("name").toString();
                    String countryNameformJason = city.get("country").toString();

                    try {
                        items.add(new WeatherDataModel(dt, temp, mintemp, maxtemp, precipitations, icon, cloudsPercentage,
                                windSpeed, cityNamefromJson, countryNameformJason));
                    } catch (ParseException ex) {
                        Logger.getLogger(ProcessJsonImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            Gson gson = new Gson();

            return gson.toJson(items);
        } else {
            return "Bad Response";
        }
    }

    @Override
    public String jSonParse(String cityName) {
        String url = Constants.HTTP_ADDRESS
                + ("q=" + cityName)
                + Constants.API_KEY;
        Response response = getResponce(url);
        WeatherDataModel model = null;

        if (response.getStatus() == 200) {
            String json = response.readEntity(String.class);
            JsonParser parser = new JsonParser();

            JsonElement jsonTree = parser.parse(json);
            List<WeatherDataModel> items = new ArrayList<>();
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();

                String dt = jsonObject.get("dt").toString();

                JsonObject coord = jsonObject.getAsJsonObject("coord");
                String lat = coord.get("lat").toString();
                String lon = coord.get("lon").toString();

                JsonObject main = jsonObject.getAsJsonObject("main");
                String temp = main.get("temp").toString();
                String mintemp = main.get("temp_min").toString();
                String maxtemp = main.get("temp_max").toString();
                JsonArray weatherList = (JsonArray) jsonObject.getAsJsonArray("weather");
                String precipitations = "";
                String icon = "";
                for (int j = 0; j < weatherList.size(); j++) {
                    JsonObject weather = (JsonObject) weatherList.get(0);
                    precipitations = weather.get("description").toString();
                    icon = Constants.IMAGE_ICON + weather.get("icon").toString().substring(1, weather.get("icon").toString().length() - 1) + ".png";
                }

                JsonObject clouds = jsonObject.getAsJsonObject("clouds");
                String cloudsPercentage = clouds.get("all").toString();
                JsonObject wind = jsonObject.getAsJsonObject("wind");
                String windSpeed = wind.get("speed").toString();
                JsonObject city = jsonObject.getAsJsonObject("sys");
                String cityNamefromJson = jsonObject.get("name").toString();
                String countryNameformJason = city.get("country").toString();

                try {
                    model = new WeatherDataModel(dt, temp, mintemp, maxtemp, precipitations, icon, cloudsPercentage,
                            windSpeed, cityNamefromJson, countryNameformJason, lat, lon);
                } catch (ParseException ex) {
                    Logger.getLogger(ProcessJsonImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                items.add(model);
            }

            Gson gson = new Gson();

            return gson.toJson(items);
        } else {
            return "Bad Response";
        }
    }

    @Override
    public String jSonParseMap(String layer, String zoom, String lat, String lon) {
        String url = Constants.HTTP_MAP_ADDRESS
                + (layer + "/" + zoom + "/")
                + lat + "/" + lon + ".png"
                + Constants.MAP_API_KEY;

        Response response = getResponce(url);

        if (response.getStatus() == 200) {

            return url;
        } else {
            return "Bad Response";
        }
    }



    

    private Response getResponce(String url) {

        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(url);
        Invocation.Builder request = resource.request();
        Response response = request.get();
        return response;
    }

}
