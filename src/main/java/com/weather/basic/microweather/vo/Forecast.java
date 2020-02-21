package com.weather.basic.microweather.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Forecast implements Serializable {
    private static final long serialVersionUID = -7622391310256147056L;
    private String date;
    private String high;
    private String fengxiang;
    private String low;
    private String fengli;
    private String type;
}
