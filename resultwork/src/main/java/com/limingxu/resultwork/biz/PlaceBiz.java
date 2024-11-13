package com.limingxu.resultwork.biz;

import com.limingxu.resultwork.entity.Place;
import com.limingxu.resultwork.mapper.PlaceMapper;
import com.limingxu.resultwork.mapper.StudentMapper;
import com.limingxu.resultwork.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class PlaceBiz {
    @Autowired
    private PlaceMapper placeMapper;

    public List<Place> getAllPlaces() {
        return placeMapper.getAllPlaces();

    }

    public void addPlace(Place place) {
       placeMapper.addPlace(place);
    }

    public boolean updatePlace(Place place) {
         return placeMapper.updatePlace(place)>0;
    }

    public boolean deletePlace(int id) {
        return placeMapper.deletePlace(id)>0;
    }
    public void setPlaceMapper(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }
}
