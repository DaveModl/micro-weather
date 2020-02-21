package com.weather.basic.microweather.job;

import com.weather.basic.microweather.service.CityDataService;
import com.weather.basic.microweather.service.WeatherDataServie;
import com.weather.basic.microweather.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataServie weatherDataServie;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("天气数据同步");
        List<City> cities = null;
            try {
                cities = cityDataService.getCityList();
            } catch (Exception e) {
                log.error("获取城市信息异常" + e);
        }
            for (City city : cities){
                String cityId = city.getCityId();
                log.info("天气数据同步中，cityId" + cityId);
                weatherDataServie.syncDataByCityId(cityId);
            }
            log.info("数据同步完成");
    }
}
