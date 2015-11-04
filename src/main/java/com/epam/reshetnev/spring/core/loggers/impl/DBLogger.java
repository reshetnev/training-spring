package com.epam.reshetnev.spring.core.loggers.impl;

import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class DBLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    private DateTimeFormatter dtf;

    public DBLogger(JdbcTemplate jdbcTemplate, DateTimeFormatter dtf) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.dtf = dtf;
    }

    @Override
    public void logEvent(Event event) {

        jdbcTemplate.update("INSERT INTO event (id, message) VALUES (?,?)", event.getId(),
                event.getMsg() + " " + event.getLocalDateTime().format(dtf));
    }
}
