package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  public String getAddress() {
    return address;
  }

  public String getDescription() {
    return description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public List<Days> getDays() {
    return days;
  }
}
