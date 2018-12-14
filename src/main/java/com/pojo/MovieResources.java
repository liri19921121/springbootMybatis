package com.pojo;

public class MovieResources {
    private Long id;

    private String placeColumn;

    private String fullColumn;

    private String name;

    private String mp4Url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceColumn() {
        return placeColumn;
    }

    public void setPlaceColumn(String placeColumn) {
        this.placeColumn = placeColumn == null ? null : placeColumn.trim();
    }

    public String getFullColumn() {
        return fullColumn;
    }

    public void setFullColumn(String fullColumn) {
        this.fullColumn = fullColumn == null ? null : fullColumn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url == null ? null : mp4Url.trim();
    }
}