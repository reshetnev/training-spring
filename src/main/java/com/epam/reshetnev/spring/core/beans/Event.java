package com.epam.reshetnev.spring.core.beans;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Event {

    private int id = getRandomNumberInRange(1, Integer.MAX_VALUE-1);

    private String msg;

    private LocalDateTime localDateTime;

    private DateTimeFormatter dtf;

    public Event(LocalDateTime localDateTime, DateTimeFormatter dtf) {

        this.localDateTime = localDateTime;
        this.dtf = dtf;
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();

        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    public boolean isDay() {

        LocalTime localTime = localDateTime.toLocalTime();

        boolean result = ((localTime.getHour() >= 8)&&(localTime.getHour() <= 17));

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Event [id: " + id + ", msg: " + msg + ", localDateTime: " + localDateTime.format(dtf) + "]";
    }

}
