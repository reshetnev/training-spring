package com.epam.reshetnev.spring.core.loggers.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class DBLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {

        jdbcTemplate.update("INSERT INTO event (id, message) VALUES (?,?)", null, event.toString());
    }
}
