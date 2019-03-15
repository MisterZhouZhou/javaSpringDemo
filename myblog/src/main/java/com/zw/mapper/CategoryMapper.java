package com.zw.mapper;

import com.zw.model.Article;
import com.zw.model.Categories;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/7/17 20:54
 * Describe: 分类sql
 */
@Mapper
@Repository
public interface CategoryMapper {

    @Insert("insert into categories(categoryName) values(#{categoryName})")
    void insertCategory(Categories category);

    @Select("select id,categoryName from categories where id=#{id}")
    Categories findCategoryById(long id);

    @Select("select id,categoryName from categories order by id desc")
    List<Categories> findAllCategories();

    @Select("select categoryName from categories")
    List<String> findCategoriesName();

    @Select("select count(*) from categories")
    int countCategoriesNum();

    @Delete("delete from categories where id=#{categoryId}")
    void deleteByArticleId(long categoryId);

}
