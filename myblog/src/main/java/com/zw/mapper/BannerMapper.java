package com.zw.mapper;

import com.zw.model.Article;
import com.zw.model.Banner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Describe: banner sql
 */
@Mapper
@Repository
public interface BannerMapper {
    @Insert("insert into banner(bannerName) values(#{bannerName})")
    void insertBanner(Banner bannerName);

    @Select("select * from banner order by id desc")
    List<Banner> findBanners();

    @Select("select * from banner where id=#{bannerId}")
    Banner findBannerById(int bannerId);

    @Select("select count(*) from banner")
    int countBannersNum();

    @Update("update banner b set b.bannerName=#{bannerName}, b.show=#{show} where b.id=#{id}")
    void updateBannerByBanner(Banner banner);
}
