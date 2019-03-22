package com.zw.mapper;

import com.zw.model.CarCategories;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Describe: CarCategories sql
 */
@Mapper
@Repository
public interface CarCategoriesMapper {

    @Select("select * from car_categories")
    List<CarCategories> findCarCategories();

}
