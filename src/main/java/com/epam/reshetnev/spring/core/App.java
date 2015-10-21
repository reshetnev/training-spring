package com.epam.reshetnev.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.beans.Client;
import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger eventLogger;

    private Event event;

    public App(Client client, EventLogger eventLogger, Event event) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.event = event;
    }

    private void logEvent(String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        for (int i = 1; i < 10 ; i++) {
            app.logEvent("Some event for "+i);
        }
    }

}
