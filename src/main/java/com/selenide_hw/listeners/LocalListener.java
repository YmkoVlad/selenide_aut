package com.selenide_hw.listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocalListener implements LogEventListener {

    private static final Logger LOGGER = LogManager.getLogger(LocalListener.class);

    @Override
    public void afterEvent(LogEvent logEvent) {
        LOGGER.info(logEvent);

    }

    @Override
    public void beforeEvent(LogEvent logEvent) {

    }
}
