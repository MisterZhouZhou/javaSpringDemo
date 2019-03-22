package com.zw.mapper;

import com.zw.model.Article;
import com.zw.model.Car;
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

    @Select("select carName,carLength,carColor,carPrice from car where carCategoriesId=#{carCategoriesId}")
    List<Car> findCarsByCarCategoriesId(@Param("carCategoriesId") int carCategoriesId);

}
