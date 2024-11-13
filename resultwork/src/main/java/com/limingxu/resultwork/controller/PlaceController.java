package com.limingxu.resultwork.controller;

import com.limingxu.resultwork.biz.PlaceBiz;
import com.limingxu.resultwork.entity.Place;
import com.limingxu.resultwork.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")

public class PlaceController {
    @Autowired
    private PlaceBiz biz;

    @GetMapping("/list")
    public Map<String, Object> listPlaces() {
        List<Place> places = biz.getAllPlaces();
        Map<String, Object> response = new HashMap<>();
        response.put("isOk", true);
        response.put("places", places);
        response.put("msg", "查询成功");
        return response;
    }

    @PostMapping("/del")
    public Map<String, Object> deletePlace(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        Map<String, Object> map = new HashMap<>();

        try {
            boolean isDeleted = biz.deletePlace(id);
            if (isDeleted) {
                map.put("isOk", true);
                map.put("msg", "删除成功");
            } else {
                map.put("isOk", false);
                map.put("msg", "删除失败");
            }
        } catch (DataIntegrityViolationException e) {
            map.put("isOk", false);
            map.put("msg", "请先删除与该场地相关的学生");
        } catch (Exception e) {
            map.put("isOk", false);
            map.put("msg", "删除失败");
        }

        return map;
    }

    @PostMapping("/add")
    public Map<String, Object> addPlace(@RequestBody Place place) {
        biz.addPlace(place);
        Map<String, Object> response = new HashMap<>();
        response.put("isOk", true);
        response.put("msg", "添加成功");
        return response;
    }

    @PostMapping("/update")
    public Map<String, Object> updatePlace(@RequestBody Place place) {
        boolean isUpdated = biz.updatePlace(place);
        Map<String, Object> map = new HashMap<>();
        if (isUpdated) {
            map.put("isOk", true);
            map.put("msg", "更新成功");
        } else {
            map.put("isOk", false);
            map.put("msg", "更新失败");
        }
        return map;
    }
}
