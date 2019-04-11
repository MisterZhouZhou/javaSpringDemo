package com.zw.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherCityRoot {

    @XmlElement(name = "c")
    private List<WeatherCityList> weatherCityList;

    public List<WeatherCityList> getWeatherCityList() {
        return weatherCityList;
    }

    public void setWeatherCityList(List<WeatherCityList> weatherCityList) {
        this.weatherCityList = weatherCityList;
    }
}
