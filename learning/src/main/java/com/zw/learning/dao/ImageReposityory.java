package com.zw.learning.dao;

import com.zw.learning.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ImageReposityory extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

//    @Query(nativeQuery = true, value = "select * from t_images ORDER BY ?#{#pageable}",
//            countQuery = "select count(*) from t_images")
//    Page<Image> findAllByPageable(Pageable pageable);

}
