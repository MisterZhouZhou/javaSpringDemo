package com.zw.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherCityList {

    @XmlElement(name = "d")
    private List<WeatherCity> weatherCityList;

    public List<WeatherCity> getWeatherCityList() {
        return weatherCityList;
    }

    public void setWeatherCityList(List<WeatherCity> weatherCityList) {
        this.weatherCityList = weatherCityList;
    }
}
