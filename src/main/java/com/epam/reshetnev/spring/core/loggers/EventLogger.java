package com.epam.reshetnev.spring.core.loggers;

import com.epam.reshetnev.spring.core.beans.Event;

public interface EventLogger {

    public void logEvent(Event event);
}
