package org.demo.springboot.service.impl;

import org.demo.springboot.dao.CityDao;
import org.demo.springboot.domain.City;
import demo.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findCityByName(String cityName) {
        return cityDao.findByName(cityName);
    }

}
