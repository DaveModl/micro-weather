package com.weather.basic.microweather.service;

import com.weather.basic.microweather.vo.Weather;
import com.weather.basic.microweather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private WeatherDataServie weatherDataServie;

    public Weather getWeatherReport(String id){
        WeatherResponse resp = weatherDataServie.getDataByCityId(id);
        return resp.getData();
    }
}
