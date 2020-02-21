package com.weather.basic.microweather.controller;

import com.weather.basic.microweather.service.WeatherDataServie;
import com.weather.basic.microweather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherDataServie weatherDataServie;

    @GetMapping("/cityid/{id}")
    public WeatherResponse getDataByCityId(@PathVariable("id") String id){
        return weatherDataServie.getDataByCityId(id);
    }

    @GetMapping("/cityname/{name}")
    public WeatherResponse getDataByCityName(@PathVariable("name") String name){
        return weatherDataServie.getDataByCityName(name);
    }

}
