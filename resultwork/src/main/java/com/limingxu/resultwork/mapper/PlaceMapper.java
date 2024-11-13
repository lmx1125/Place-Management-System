package com.limingxu.resultwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limingxu.resultwork.entity.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PlaceMapper extends BaseMapper<Place> {
    @Select("select * from t_place")
    List<Place> getAllPlaces();

    @Select("SELECT * FROM t_place WHERE id = #{id}")
    Place selectPlaceById(int id);
    @Select("select * from t_place where id#{id}")
    Place selectPlaceByName(String name);
    @Insert("INSERT INTO t_place(name, address, max_accommodate) VALUES(#{name}, #{address}, #{maxAccommodate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addPlace(Place place);

    @Delete("delete from t_place where id=#{id}")
    int deletePlace(int id);
    @Update("UPDATE t_place SET name=#{name}, address=#{address}, max_accommodate=#{maxAccommodate} WHERE id=#{id}")
    int updatePlace(Place place);
}
