package com.zw.mapper;

import com.zw.model.Article;
import com.zw.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/7/22 20:22
 * Describe: 产品sql
 */
@Mapper
@Repository
public interface ProductMapper {

    @Insert("insert into product(name,price,date) values(#{name},#{price},#{date})")
    void insert(Product product);

    @Select("select name,price,date from product")
    List<Product> findAllProducts();
}
