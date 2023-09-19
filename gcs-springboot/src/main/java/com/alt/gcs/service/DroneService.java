package com.alt.gcs.service;

import com.alt.gcs.controller.DroneController;
import io.mavsdk.System;
import io.mavsdk.action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
@Component
public class DroneService {
    private static final Logger logger = LoggerFactory.getLogger(DroneService.class);
    //@Autowired
    //TODO: fix auto wiring
    System drone = new System();

    public void arm() {
        //System drone = new System("localhost", 50000);
        CountDownLatch latch = new CountDownLatch(1);
        drone.getAction().arm()
                .doOnComplete(() -> logger.debug("Arming..."))
                .doOnError(throwable -> logger.error("Failed to arm: "
                        + ((Action.ActionException) throwable).getCode()))
                .subscribe(latch::countDown, throwable -> latch.countDown());

        try {
            latch.await();
        } catch (InterruptedException ignored) {
            // This is expected
        }
    }

    public void disArm() {
        //System drone = new System("localhost", 50000);
        CountDownLatch latch = new CountDownLatch(1);
        drone.getAction().disarm()
                .doOnComplete(() -> logger.debug("DisArming..."))
                .doOnError(throwable -> logger.error("Failed to dis-arm: "
                        + ((Action.ActionException) throwable).getCode()))
                .subscribe(latch::countDown, throwable -> latch.countDown());

        try {
            latch.await();
        } catch (InterruptedException ignored) {
            // This is expected
        }
    }

    public void takeOff() {
        //System drone = new System("localhost", 50000);
        CountDownLatch latch = new CountDownLatch(1);
        drone.getAction().takeoff()
                .doOnComplete(() -> logger.debug("Taking Off..."))
                .doOnError(throwable -> logger.error("Failed to take off: "
                        + ((Action.ActionException) throwable).getCode()))
                .subscribe(latch::countDown, throwable -> latch.countDown());

        try {
            latch.await();
        } catch (InterruptedException ignored) {
            // This is expected
        }
    }

    public void land() {
        //System drone = new System("localhost", 50000);
        CountDownLatch latch = new CountDownLatch(1);
        drone.getAction().land()
                .doOnComplete(() -> logger.debug("Landing..."))
                .doOnError(throwable -> logger.error("Failed to land: "
                        + ((Action.ActionException) throwable).getCode()))
                .subscribe(latch::countDown, throwable -> latch.countDown());

        try {
            latch.await();
        } catch (InterruptedException ignored) {
            // This is expected
        }
    }
}
