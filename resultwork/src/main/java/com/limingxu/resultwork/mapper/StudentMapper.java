package com.limingxu.resultwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limingxu.resultwork.entity.Student;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT s.*, p.name as place_name, p.address as place_address, p.max_accommodate as place_max_accommodate " +
            "FROM t_student s LEFT JOIN t_place p ON s.place_id = p.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "place_id", property = "place.id"),
            @Result(column = "place_name", property = "place.name"),
            @Result(column = "place_address", property = "place.address"),
            @Result(column = "place_max_accommodate", property = "place.maxAccommodate"),
            @Result(column = "place_id", property = "place",
                    one = @One(select = "com.limingxu.resultwork.mapper.PlaceMapper.selectPlaceById"))
    })
    List<Student> selectAllStudentsAndPlaces();

    @Select("SELECT * FROM t_student WHERE id=#{id}")
    Student selectStudentById(int id);

    @Select("SELECT * FROM t_student WHERE name=#{name}")
    Student selectStudentByName(String name);

    @Insert("INSERT INTO t_student(name, gender, begin_time, place_id) VALUES(#{name}, #{gender}, #{beginTime}, #{place.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addStudent(Student student);

    @Delete("DELETE FROM t_student WHERE id=#{id}")
    int deleteStudentById(int id);

    @Update("UPDATE t_student SET name=#{name}, gender=#{gender}, begin_time=#{beginTime}, place_id=#{place.id} WHERE id=#{id}")
    int updateStudent(Student student);
}
