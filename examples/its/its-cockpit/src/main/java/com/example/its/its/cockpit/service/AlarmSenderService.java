package com.example.its.its.cockpit.service;


import com.example.its.its.cockpit.dto.web.alarm.AlarmDto;

public interface AlarmSenderService {

    void email(AlarmDto alarm);
    void sms(AlarmDto alarm);
}
