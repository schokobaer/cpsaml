package com.example.roomba.driver.schedule;

import com.example.roomba.driver.repo.RoombaRepo;
import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RoombaSensorReadScheduler {

    private  RoombaJSSC roomba;
    private long lastCall;

    public RoombaSensorReadScheduler() {
        roomba = new RoombaJSSCSerial();
        roomba.connect("/dev/ttyUSB0");
        roomba.startup();
    }

    @Scheduled(fixedRate = 55L)
    public void update() {
        try {
            roomba.updateSensors();
        } catch (Exception e) {

        }

        RoombaRepo.roomba.setBatteryCapacity(roomba.batteryCapacity());
        RoombaRepo.roomba.setBatteryCharge(roomba.batteryCharge());
        RoombaRepo.roomba.setMotorCurrentLeft(roomba.motorCurrentLeft());
        RoombaRepo.roomba.setMotorCurrentRight(roomba.motorCurrentRight());
        RoombaRepo.roomba.setDocked(roomba.homebaseChargerAvailable());
        RoombaRepo.roomba.setBumpedLeft(roomba.bumpLeft());
        RoombaRepo.roomba.setBumpedRight(roomba.bumpRight());
        RoombaRepo.roomba.setWheelDropLeft(roomba.wheelDropLeft());
        RoombaRepo.roomba.setWheelDropRight(roomba.wheelDropRight());


        roomba.wheelOvercurrentLeft()
    }
}
