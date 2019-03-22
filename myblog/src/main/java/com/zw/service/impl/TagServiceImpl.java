package com.zw.service.impl;

import com.zw.mapper.TagMapper;
import com.zw.model.Tag;
import com.zw.service.TagService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/7/16 19:50
 * Describe:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public void addTags(String[] tags, int tagSize) {
        for(String tag : tags){
            if(tagMapper.findIsExitByTagName(tag) == 0){
                Tag t = new Tag(tag, tagSize);
                tagMapper.insertTag(t);
            }
        }
    }

    @Override
    public JSONObject findTagsCloud() {
        List<Tag> tags = tagMapper.findTagsCloud();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        jsonObject.put("result",JSONArray.toJSON(tags));
        jsonObject.put("tagsNum",tags.size());
        return jsonObject;
    }

    @Override
    public int countTagsNum() {
        return tagMapper.countTagsNum();
    }

    @Override
    public int getTagsSizeByTagName(String tagName) {
        return tagMapper.getTagsSizeByTagName(tagName);
    }
}
