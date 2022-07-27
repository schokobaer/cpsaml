package com.example.roomba.driver.rest;

import com.example.roomba.driver.dto.roomba.RoombaDto;
import com.example.roomba.driver.repo.RoombaRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoombaRestController {

    @GetMapping("/roomba")
    public RoombaDto get() {
        return RoombaRepo.roomba;
    }
}
