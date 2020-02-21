package com.weather.basic.microweather.vo;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CityList {
    @XmlElement(name = "d")
    private List<City> cityList;
}
