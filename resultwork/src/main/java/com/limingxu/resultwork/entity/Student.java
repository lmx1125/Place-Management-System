package com.limingxu.resultwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;
@TableName("t_student")
public class Student {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private String gender;
    private Date beginTime;
    @TableField(exist = false)
    private Place place;

    public Student() {
    }

    public Student(String name, int id, String gender, Date beginTime, Place place) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.beginTime = beginTime;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
