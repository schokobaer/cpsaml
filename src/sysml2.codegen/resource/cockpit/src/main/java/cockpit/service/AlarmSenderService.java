package «pkg».cockpit.service;


import «pkg».cockpit.dto.web.alarm.AlarmDto;

public interface AlarmSenderService {

    void email(AlarmDto alarm);
    void sms(AlarmDto alarm);
}
