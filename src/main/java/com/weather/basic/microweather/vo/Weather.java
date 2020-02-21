package com.weather.basic.microweather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Weather implements Serializable {
    private static final long serialVersionUID = 3189375327209703294L;
    private String city;
    private String aqi;
    private String wendu;
    private String ganmao;
    private Yesterday yesterday;
    private List<Forecast> forecast;
}
