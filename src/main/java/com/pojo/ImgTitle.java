package com.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ImgTitle {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}