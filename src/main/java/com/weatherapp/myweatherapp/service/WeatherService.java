package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class WeatherService {

  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public CityInfo getCityWithLongestDaylight(String cityOne, String cityTwo) {

    CityInfo cityOneInfo = weatherRepo.getByCity(cityOne);
    long daylightTimeCityOne = this.getDaylightTime(cityOneInfo.getCurrentConditions().getSunrise(), cityOneInfo.getCurrentConditions().getSunset());

    CityInfo cityTwoInfo = weatherRepo.getByCity(cityTwo);
    long daylightTimeCityTwo = this.getDaylightTime(cityTwoInfo.getCurrentConditions().getSunrise(), cityTwoInfo.getCurrentConditions().getSunset());

    if (daylightTimeCityOne > daylightTimeCityTwo) {
      return cityOneInfo;
    }
    return cityTwoInfo;
  }

  private long getDaylightTime(String sunriseString, String sunsetString) {
    LocalTime sunset = LocalTime.parse(sunsetString, FORMATTER);
    LocalTime sunrise = LocalTime.parse(sunriseString, FORMATTER);
    return ChronoUnit.SECONDS.between(sunset, sunrise);
  }

  public CityInfo getRainingCity(String cityOne, String cityTwo) {

    CityInfo cityOneInfo = weatherRepo.getByCity(cityOne);
    boolean rainingInCityOne = this.isCurrentlyRaining(cityOneInfo);

    CityInfo cityTwoInfo = weatherRepo.getByCity(cityTwo);
    boolean rainingInCityTwo = this.isCurrentlyRaining(cityTwoInfo);
    

    if (rainingInCityOne) {
      return cityOneInfo;
    } else if (rainingInCityTwo) {
      return cityTwoInfo;
    }else {
      return new CityInfo();
    }

  }

  private boolean isCurrentlyRaining(CityInfo cityInfo) {
    return cityInfo.getCurrentConditions().getConditions().contains("rain");
  }
}
