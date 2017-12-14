package com.example.pp03.peralppay.work.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pp03 on 2017/12/1.
 */

public class MenuBean implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(img);
        dest.writeDouble(moneny);
        dest.writeString(remark);
        dest.writeInt(size);
        dest.writeDouble(summoneny);
    }
    // 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
    // android.os.BadParcelableException:
    // Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.um.demo.Person
    // 2.这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
    // 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
    // 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
    // 5.反序列化对象
    public static final Parcelable.Creator<MenuBean> CREATOR = new Creator(){

        @Override
        public MenuBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            MenuBean p = new MenuBean();
            p.setName(source.readString());
            p.setImg(source.readString());
            p.setMoneny(source.readDouble());
            p.setRemark(source.readString());
            p.setSize(source.readInt());
            p.setSummoneny(source.readDouble());
            return p;
        }

        @Override
        public MenuBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new MenuBean[size];
        }
    };
}
