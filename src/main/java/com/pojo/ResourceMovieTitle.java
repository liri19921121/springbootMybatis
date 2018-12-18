package com.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ResourceMovieTitle {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String title;

    private String titleUrl;

    private String indexColumn;

    private String type;

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

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl == null ? null : titleUrl.trim();
    }

    public String getIndexColumn() {
        return indexColumn;
    }

    public void setIndexColumn(String indexColumn) {
        this.indexColumn = indexColumn == null ? null : indexColumn.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}