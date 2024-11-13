package com.limingxu.resultwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_place")
public class Place {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private String address;
    private int maxAccommodate;

    public Place() {
    }

    public Place(int id, String name, String address, int maxAccommodate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxAccommodate = maxAccommodate;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxAccommodate() {
        return maxAccommodate;
    }

    public void setMaxAccommodate(int maxAccommodate) {
        this.maxAccommodate = maxAccommodate;
    }
}

