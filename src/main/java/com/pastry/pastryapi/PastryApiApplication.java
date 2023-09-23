package com.pastry.pastryapi;

import com.pastry.pastryapi.utils.DataInitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication

public class PastryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PastryApiApplication.class, args);
    }
    @Autowired
    private DataInitializationService dataInitializationService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDataOnStartup() {
        dataInitializationService.initializeData();
    }
}
