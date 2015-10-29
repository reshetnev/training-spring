package com.epam.reshetnev.spring.core.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;
import com.epam.reshetnev.spring.core.loggers.impl.FileEventLogger;

@Aspect
public class StatisticsAspect {

    private Integer max_allowed;

    private Map<Class<?>, Integer> counter = new HashMap<Class<?>, Integer>();

    private EventLogger otherLogger;

    public StatisticsAspect(Integer max_allowed, EventLogger otherLogger) {
        this.max_allowed = max_allowed;
        this.otherLogger = otherLogger;
    }

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    public void setCounter(Map<Class<?>, Integer> counter) {
        this.counter = counter;
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.loggers.impl.FileEventLogger.logEvent(..))")
    private void fileLoggerMethods() {
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {

        Class<?> clazz = jp.getTarget().getClass();

        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }

        counter.put(clazz, counter.get(clazz)+1);
    }

    @Around("fileLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Event evt) throws Throwable {

        Class<?> clazz = FileEventLogger.class;

        Integer count = counter.containsKey(clazz) ? counter.get(clazz) : 0;

        if (count < max_allowed) {
            jp.proceed(new Event[] {evt});
        } else {
            otherLogger.logEvent(evt);
        }
    }
}
