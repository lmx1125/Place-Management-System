package com.limingxu.resultwork.biz;

import com.limingxu.resultwork.entity.Place;
import com.limingxu.resultwork.entity.Student;
import com.limingxu.resultwork.mapper.PlaceMapper;
import com.limingxu.resultwork.mapper.StudentMapper;
import com.limingxu.resultwork.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentBiz {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PlaceMapper placeMapper;
    public List<Student> findAll(){
        return studentMapper.selectAllStudentsAndPlaces();
    }
    @Transactional
    public void addStudentWithPlace(Student student) {
        Place place = student.getPlace();
        placeMapper.addPlace(place);
        student.setPlace(place);
        studentMapper.addStudent(student);
    }
    public Student searhStudent(String name) {
        return studentMapper.selectStudentByName(name);
    }
    public boolean updateStudent(Student student) {
        return studentMapper.updateStudent(student)>0;
    }
    public boolean delStudent(int id){
        return this.studentMapper.deleteStudentById(id)>0;
    }
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
    public void setPlaceMapper(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }
}
