package com.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TestFor {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String title;

    private String isDown;

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

    public String getIsDown() {
        return isDown;
    }

    public void setIsDown(String isDown) {
        this.isDown = isDown == null ? null : isDown.trim();
    }
}