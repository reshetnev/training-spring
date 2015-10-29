package com.epam.reshetnev.spring.core;

import java.io.IOException;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.aspect.StatisticsAspect;
import com.epam.reshetnev.spring.core.beans.Client;
import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.enums.EventType;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class App {

    private Client client;

    private EventLogger defaultLogger;

    private Map<EventType, EventLogger> loggers;

    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    private void logEvent(Event event, EventType eventType) throws IOException {

        String msg = event.getMsg();
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message+", greeting: "+client.getGreeting());

        EventLogger eventLogger = loggers.get(eventType);

        if (eventLogger == null) {
            eventLogger = defaultLogger;
        }

        eventLogger.logEvent(event);
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        for (int i = 1; i < 10 ; i++) {
            Event event = (Event) ctx.getBean("event");
            event.setMsg("Some event for "+i);
            app.logEvent(event, null);
            Thread.sleep(1000);
        }

        StatisticsAspect statisticsAspect = ctx.getBean(StatisticsAspect.class);
        System.out.println(statisticsAspect.getCounter().toString());
        
        ctx.close();
    }

}
