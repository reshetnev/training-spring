package com.epam.reshetnev.spring.core.loggers.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.epam.reshetnev.spring.core.beans.Event;
import com.epam.reshetnev.spring.core.loggers.EventLogger;

public class FileEventLogger implements EventLogger {

    private String fileName;

    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {

        this.file = new File(fileName);

        if (!file.canWrite()) {
            throw new IOException();
        }
    }

    @Override
    public void logEvent(Event event) throws IOException {

        FileUtils.writeStringToFile(file, event.toString()+"\n", true);
    }

}
