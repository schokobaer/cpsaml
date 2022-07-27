package com.example.its.its.systemwidedt.repo;

import com.example.its.its.systemwidedt.dto.roomba.RoombaDto;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoombaRepo {

    private Map<String, ImmutablePair<RoombaDto, ZonedDateTime>> data = new HashMap<>();

    public void put(RoombaDto roomba) {
        data.put(roomba.getId(), new ImmutablePair<>(roomba, ZonedDateTime.now()));
    }
	
    public Collection<ImmutablePair<RoombaDto, ZonedDateTime>> getAll() {
        return data.values();
    }
}
