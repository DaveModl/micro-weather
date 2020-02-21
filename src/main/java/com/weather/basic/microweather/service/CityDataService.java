package com.weather.basic.microweather.service;

import com.weather.basic.microweather.vo.City;
import com.weather.basic.microweather.vo.CityList;
import com.weather.basic.microweather.util.XmlToObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataService {
    public List<City> getCityList() throws IOException, JAXBException {
        Resource resource = new ClassPathResource("china_city_id.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }
        bufferedReader.close();

        CityList cityList = (CityList) XmlToObject.trans(CityList.class,stringBuffer.toString());
        return cityList.getCityList();
    }
}
