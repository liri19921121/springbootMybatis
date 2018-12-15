package com.pojo;

public class ImgPath {
    private Long id;

    private Long imgId;

    private String imgPath;

    private Integer isDown;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Integer getIsDown() {
        return isDown;
    }

    public void setIsDown(Integer isDown) {
        this.isDown = isDown;
    }
}