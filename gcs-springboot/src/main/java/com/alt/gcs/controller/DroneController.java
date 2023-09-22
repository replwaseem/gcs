package com.alt.gcs.controller;

import com.alt.gcs.domain.Battery;
import com.alt.gcs.domain.Position;
import com.alt.gcs.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class DroneController {
    private static final Logger logger = LoggerFactory.getLogger(DroneController.class);

    @Autowired
    DroneService droneService;

    @PostMapping("/arm")
    public void arm() {
        logger.info("Received command to arm the drone..");
        droneService.arm();
    }

    @PostMapping("/disarm")
    public void disArm() {
        logger.info("Received command to disarm the drone..");
        droneService.disArm();
    }

    @PostMapping("/land")
    public void land() {
        logger.info("Received command to land the drone..");
        droneService.land();
    }

    @PostMapping("/takeoff")
    public void takeOff() {
        logger.info("Received command to take off the drone..");
        droneService.takeOff();
    }

    @GetMapping("/fetchlog")
    public void fetchLogs() {
        logger.info("Received command to fetch logs from the drone..");
       // droneService.fetchLogs();
        droneService.fetchPosition();
    }

    @GetMapping("/position")
    public Position fetPosition() {
        logger.info("Received command to get position of the drone..");
        // droneService.fetchLogs();
        droneService.fetchPosition();
        return droneService.getPosition();
    }

    @GetMapping("/battery")
    public Battery getBatteryInfo() {
        logger.info("Received command to get battery of drone..");
        // droneService.fetchLogs();
        droneService.fetchBattery();
        return droneService.getBattery();
    }


}
