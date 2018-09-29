package com.zw.learning.service;

import com.zw.learning.entity.Image;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ImageService {

    void saveImage(Image img);

    List<Image> findAllImageList();

    Page<Image> findAllImageListByPage(Integer page, Integer size);
}
