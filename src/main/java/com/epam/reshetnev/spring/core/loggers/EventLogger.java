package com.epam.reshetnev.spring.core.loggers;

import java.io.IOException;

import com.epam.reshetnev.spring.core.beans.Event;

public interface EventLogger {

    public void logEvent(Event event) throws IOException;
}
