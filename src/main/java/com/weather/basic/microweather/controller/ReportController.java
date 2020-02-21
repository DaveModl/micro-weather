package com.weather.basic.microweather.controller;

import com.weather.basic.microweather.service.CityDataService;
import com.weather.basic.microweather.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private ReportService reportService;
    @GetMapping("/cityid/{id}")
    public ModelAndView getReportByCityId(@PathVariable("id") String id, Model model) throws IOException, JAXBException {
       model.addAttribute("title","天气预报");
       model.addAttribute("cityId",id);
       model.addAttribute("cityList",cityDataService.getCityList());
       model.addAttribute("report",reportService.getWeatherReport(id));
       return new ModelAndView("weather/report","reportModel",model);
    }
}
