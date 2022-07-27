package com.example.its.its.cockpit.log;

import com.example.its.its.cockpit.dto.web.cps.CpsDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class CpsLogEntry {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime timestamp;

    private CpsLogEvent event;
    private String msg;

    @JsonIgnore
    private CpsDto dto;

    public CpsLogEntry() { }

    public CpsLogEntry(CpsLogEvent event, String msg, CpsDto dto) {
        timestamp = LocalDateTime.now();
        this.event = event;
        this.msg = msg;
        this.dto = dto;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public CpsLogEvent getEvent() {
        return event;
    }

    public void setEvent(CpsLogEvent event) {
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CpsDto getDto() {
        return dto;
    }

    public void setDto(CpsDto dto) {
        this.dto = dto;
    }
}
