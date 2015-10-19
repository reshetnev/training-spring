package com.epam.reshetnev.spring.core.loggers.impl;

import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger  {

    public void logEvent(String msg) {
        System.out.println(msg);
    }

}
