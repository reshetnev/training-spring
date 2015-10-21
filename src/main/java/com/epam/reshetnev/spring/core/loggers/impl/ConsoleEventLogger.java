package com.epam.reshetnev.spring.core.loggers.impl;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

}
