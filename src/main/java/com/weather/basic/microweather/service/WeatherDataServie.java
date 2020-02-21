package com.weather.basic.microweather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.basic.microweather.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WeatherDataServie {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";
    private final Long TIME_OUT = 1800L;

    public WeatherResponse getDataByCityId(String id){
        String uri = WEATHER_API + "?citykey=" + id;
        return getWeatherData(uri);
    }

    public WeatherResponse getDataByCityName(String name){
        String uri = WEATHER_API + "?city=" + name;
        return getWeatherData(uri);
    }

    public void syncDataByCityId(String id){
        String uri = WEATHER_API + "?citykey=" + id;
        saveDate(uri);
    }

    private void saveDate(String uri) {
        ValueOperations<String,String> ops = this.stringRedisTemplate.opsForValue();
        String key = uri;
        String body = null;
            ResponseEntity<String> resp = restTemplate.getForEntity(uri,String.class);
            if (resp.getStatusCodeValue() == 200) {
                body = resp.getBody();
            }
            ops.set(key,body,TIME_OUT, TimeUnit.SECONDS);
    }

    private WeatherResponse getWeatherData(String uri){
        ValueOperations<String,String> ops = this.stringRedisTemplate.opsForValue();
        String key = uri;
        String body = null;
        //cache
        if (!this.stringRedisTemplate.hasKey(key)){
            log.info("not find key " + key);
            ResponseEntity<String> resp = restTemplate.getForEntity(uri,String.class);
        if (resp.getStatusCodeValue() == 200) {
            body = resp.getBody();
        }
        ops.set(key,body,TIME_OUT, TimeUnit.SECONDS);
        }else {
           log.info("find key " + key + ", value=" + ops.get(key));
           body = ops.get(key);
        }
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = mapper.readValue(body,WeatherResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
