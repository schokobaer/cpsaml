package com.example.roomba.driver.repo;

import com.example.roomba.driver.dto.roomba.RoombaDto;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoombaRepo {

    public static RoombaDto roomba = new RoombaDto();
}
