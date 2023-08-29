package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  // given a city name, returns its forecast information
  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  //  given two city names, compare the length of the daylight hours and return the city with the longest day
  @GetMapping("/daylight/{city_one}/{city_two}")
  public ResponseEntity<CityInfo> longestDayLight(@PathVariable("city_one") String city_one, @PathVariable("city_two") String city_two){
    CityInfo ci = weatherService.getCityWithLongestDaylight(city_one, city_two);

    return ResponseEntity.ok(ci);
  }

  //  given two city names, check which city its currently raining in
  @GetMapping("/raining/{city_one}/{city_two}")
  public ResponseEntity<CityInfo> rainingInCity(@PathVariable("city_one") String city_one, @PathVariable("city_two") String city_two){
    CityInfo ci = weatherService.getRainingCity(city_one, city_two);

    return ResponseEntity.ok(ci);
  }

}
