package com.zw.mapper;

import com.zw.model.CarCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Describe: CarCategory sql
 */
@Mapper
@Repository
public interface CarCategoryMapper {

    @Select("select * from car_categories")
    List<CarCategory> findCarCategories();

    @Insert("insert into car_categories(carCategoryName,carCategoryIcon,carCategoryPrice) values(#{carCategoryName},#{carCategoryIcon},#{carCategoryPrice})")
    void insertCarCategory(CarCategory carCategory);

    @Select("select * from car_categories where id=#{carCategoryId}")
    CarCategory findCarCategoryById(int carCategoryId);
}
