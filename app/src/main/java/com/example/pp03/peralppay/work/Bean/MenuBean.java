package com.example.pp03.peralppay.work.Bean;

/**
 * Created by pp03 on 2017/12/1.
 */

public class MenuBean {
    private String name;
    private String img;
    private Double moneny;
    private String remark;
    private int size;
    private Double summoneny;

    public Double getSummoneny() {
        return summoneny;
    }

    public void setSummoneny(Double summoneny) {
        this.summoneny = summoneny;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double getMoneny() {
        return moneny;
    }

    public void setMoneny(Double moneny) {
        this.moneny = moneny;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
