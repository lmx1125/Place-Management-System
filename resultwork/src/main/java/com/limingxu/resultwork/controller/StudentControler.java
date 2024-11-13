package com.limingxu.resultwork.controller;

import com.limingxu.resultwork.biz.StudentBiz;
import com.limingxu.resultwork.entity.Place;
import com.limingxu.resultwork.entity.Student;
import com.limingxu.resultwork.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentControler {
    @Autowired
    private StudentBiz biz;

    @GetMapping("/list")
    public Map<String, Object> findAllStudents() {
        List<Student> students = biz.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("isOk", true);
        map.put("students", students);
        map.put("msg", "查询成功");
        return map;
    }

    @GetMapping("/find")
    public Map<String, Object> find(@RequestParam("name") String name) {
        Student student = biz.searhStudent(name);
        Map<String, Object> map = new HashMap<>();
        map.put("isOk", true);
        map.put("student", student);
        map.put("msg", "查询成功");
        return map;
    }

    @PostMapping("/del")
    public Map<String, Object> deleteStudent(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        Map<String, Object> map = new HashMap<>();

        try {
            boolean isDeleted = biz.delStudent(id);
            if (isDeleted) {
                map.put("isOk", true);
                map.put("msg", "删除成功");
            } else {
                map.put("isOk", false);
                map.put("msg", "删除失败");
            }
        } catch (Exception e) {
            map.put("isOk", false);
            map.put("msg", "删除失败");
        }

        return map;
    }

    @PostMapping("/add")
    public Map<String, Object> addStudent(@RequestBody Student student) {
        this.biz.addStudentWithPlace(student);
        Map<String, Object> map = new HashMap<>();
        map.put("isOk", true);
        map.put("msg", "添加成功");
        return map;
    }

    @PostMapping("/update")
    public Map<String, Object> updateStudent(@RequestBody Student student) {
        boolean isUpdated = biz.updateStudent(student);
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

    public void setBiz(StudentBiz biz) {
        this.biz = biz;
    }
}
