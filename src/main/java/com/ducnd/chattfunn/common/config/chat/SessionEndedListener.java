package com.ducnd.chattfunn.common.config.chat;

import com.ducnd.chattfunn.manager.SocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class SessionEndedListener implements ApplicationListener<ApplicationEvent> {
    @Autowired
    private SocketManager socketManager;

    private static final Logger LOG = LoggerFactory.getLogger(SessionEndedListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextClosedEvent) {
            socketManager.getSocketIOServer().stop();
        }

    }

}
