package com.epam.reshetnev.spring.core.loggers.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.reshetnev.spring.core.beans.Event;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event) throws IOException {

        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    public void destroy() throws IOException {

        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }

    private void writeEventFromCache() throws IOException {

        for(Event event : cache) {
            super.logEvent(event);
        }
    }
}
