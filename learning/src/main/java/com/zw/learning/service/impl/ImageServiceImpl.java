package com.zw.learning.service.impl;

import com.zw.learning.dao.ImageReposityory;
import com.zw.learning.entity.Image;
import com.zw.learning.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageReposityory imageReposityory;

    @Override
    public void saveImage(Image img) {
        imageReposityory.save(img);
    }

    @Override
    public List<Image> findAllImageList() {
        return imageReposityory.findAll();
    }

    @Override
    public Page<Image> findAllImageListByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page-1, size, Sort.Direction.ASC, "id");
        return imageReposityory.findAll(pageable);
    }


}
