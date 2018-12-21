package com.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ResourceAsianwm {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    private String fullColumn;

    private String url;
    private String type;
    private String titleUrl;
    private String thunder;

    public String getThunder() {
        return thunder;
    }

    public void setThunder(String thunder) {
        this.thunder = thunder;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFullColumn() {
        return fullColumn;
    }

    public void setFullColumn(String fullColumn) {
        this.fullColumn = fullColumn == null ? null : fullColumn.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}