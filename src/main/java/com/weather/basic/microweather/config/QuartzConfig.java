package com.weather.basic.microweather.config;

import com.weather.basic.microweather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    private final int REFRESH = 1800;
    @Bean
    public JobDetail weatherJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherDataSyncJob")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger sampleJobTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(REFRESH)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(weatherJobDetail())
                .withIdentity("weatherJobTrigger")
                .withSchedule(simpleScheduleBuilder)
                .build();

    }
}
