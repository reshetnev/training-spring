package com.epam.reshetnev.spring.core;

import com.epam.reshetnev.spring.core.beans.Client;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {

        

    }

}
