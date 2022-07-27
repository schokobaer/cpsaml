package com.example.roomba.driver.dto.roomba;

public class RoombaDto {

    private int batteryCapacity;
    private int batteryCharge;
    private int motorCurrentLeft;
    private int motorCurrentRight;
    private boolean docked;
    private boolean bumpedLeft;
    private boolean bumpedRight;
    private boolean wheelDropLeft;
    private boolean wheelDropRight;

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(int batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public int getMotorCurrentLeft() {
        return motorCurrentLeft;
    }

    public void setMotorCurrentLeft(int motorCurrentLeft) {
        this.motorCurrentLeft = motorCurrentLeft;
    }

    public int getMotorCurrentRight() {
        return motorCurrentRight;
    }

    public void setMotorCurrentRight(int motorCurrentRight) {
        this.motorCurrentRight = motorCurrentRight;
    }

    public boolean isDocked() {
        return docked;
    }

    public void setDocked(boolean docked) {
        this.docked = docked;
    }

    public boolean isBumpedLeft() {
        return bumpedLeft;
    }

    public void setBumpedLeft(boolean bumpedLeft) {
        this.bumpedLeft = bumpedLeft;
    }

    public boolean isBumpedRight() {
        return bumpedRight;
    }

    public void setBumpedRight(boolean bumpedRight) {
        this.bumpedRight = bumpedRight;
    }

    public boolean isWheelDropLeft() {
        return wheelDropLeft;
    }

    public void setWheelDropLeft(boolean wheelDropLeft) {
        this.wheelDropLeft = wheelDropLeft;
    }

    public boolean isWheelDropRight() {
        return wheelDropRight;
    }

    public void setWheelDropRight(boolean wheelDropRight) {
        this.wheelDropRight = wheelDropRight;
    }
}
