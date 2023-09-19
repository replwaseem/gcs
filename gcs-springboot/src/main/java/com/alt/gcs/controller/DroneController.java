package com.alt.gcs.controller;

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


}
