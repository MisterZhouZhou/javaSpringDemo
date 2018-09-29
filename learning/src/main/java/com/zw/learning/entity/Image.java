package com.zw.learning.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_images")
public class Image {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "url",length = 100)
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
