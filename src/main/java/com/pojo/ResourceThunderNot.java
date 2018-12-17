package com.pojo;

public class ResourceThunderNot {
    private Long id;

    private String thunder;

    private String isDown;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThunder() {
        return thunder;
    }

    public void setThunder(String thunder) {
        this.thunder = thunder == null ? null : thunder.trim();
    }

    public String getIsDown() {
        return isDown;
    }

    public void setIsDown(String isDown) {
        this.isDown = isDown == null ? null : isDown.trim();
    }
}