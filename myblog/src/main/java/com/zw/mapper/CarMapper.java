package com.zw.mapper;

import com.zw.model.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Describe: car sql
 */
@Mapper
@Repository
public interface CarMapper {

    @Select("select carName,carLength,carColor,carPrice from car where carCategoryId=#{carCategoryId}")
    List<Car> findCarsByCarCategoryId(@Param("carCategoryId") int carCategoryId);

    @Insert("insert into car(carCategoryId,carName,carLength,carColor,carPrice) values(#{carCategoryId},#{carName},#{carLength},#{carColor},#{carPrice})")
    void insertCar(Car car);
}

