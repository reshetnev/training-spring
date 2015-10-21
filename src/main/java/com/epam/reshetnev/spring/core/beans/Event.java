package com.epam.reshetnev.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id = getRandomNumberInRange(1, Integer.MAX_VALUE-1);

    private String msg;

    private Date date;

    private DateFormat df;

    public Event(Date date, DateFormat df) {

        this.date = date;
        this.df = df;
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();

        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]";
    }

}
