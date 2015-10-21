package com.epam.reshetnev.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.beans.Client;
import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event) {

        String msg = event.getMsg();
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) throws InterruptedException {

        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        for (int i = 1; i < 10 ; i++) {
            Event event = (Event) ctx.getBean("event");
            event.setMsg("Some event for "+i);
            app.logEvent(event);
            Thread.sleep(1000);
        }
    }

}
