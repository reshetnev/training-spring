package com.epam.reshetnev.spring.core.loggers.impl;

import java.io.IOException;
import java.util.Collection;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

}
