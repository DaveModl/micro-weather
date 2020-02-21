package com.weather.basic.microweather.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = -945554791598188456L;
    private Weather data;
    private String status;
    private String desc;
}
