package com.alt.gcs.service;

import com.alt.gcs.controller.DroneController;
import com.alt.gcs.domain.Battery;
import io.mavsdk.System;
import io.mavsdk.action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import com.alt.gcs.domain.Position;
@Component
public class DroneService {
    private static final Logger logger = LoggerFactory.getLogger(DroneService.class);

    Position position1 = new Position();
    Battery battery = new Battery();
    //@Autowired
    //TODO: fix auto wiring
    // System drone = new System();
    System drone = new System("localhost", 50000);

    public void arm() {
        //System drone = new System("localhost", 50000);
        CountDownLatch latch = new CountDownLatch(1);
        drone.getAction().arm()
                .doOnComplete(() -> logger.info("Arming..."))
                .doOnError(throwable -> logger.error("Failed to arm: "
                        + ((Action.ActionException) throwable).getCode()))
                .subscribe(latch::countDown, throwable -> latch.countDown());
        // log();
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
        //    log();

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

    public void fetchLogs() {
        CountDownLatch latch = new CountDownLatch(1);


        drone.getLogFiles().getEntries()
                .toFlowable()
                .map(entries -> entries.get(entries.size() - 1))
                .flatMap(lastEntry ->
                        drone.getLogFiles().downloadLogFile(lastEntry, "/Users/waseem/code/dcode/logs/sample.ulg"))
                .map(progressData -> Math.round(progressData.getProgress() * 100))
                .filter(progressPercent -> progressPercent % 5 == 0)
                .distinctUntilChanged()
                .subscribe(
                        progressPercent -> {
                            logger.info("Progress: " + progressPercent + "%");
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            logger.error("log Error:  " + throwable.getMessage());
                            latch.countDown();
                        },
                        () -> {
                            logger.info("Successfully downloaded last log!");
                            latch.countDown();
                        });

    }

    public void fetchPosition() {
        CountDownLatch latch = new CountDownLatch(1);
        drone.getTelemetry().getPosition()
                .subscribe(
                        position -> {
                            logger.debug("position: " + position + "%");
                            position1.setAlt(position.getAbsoluteAltitudeM());
                            position1.setLat(position.getLatitudeDeg());
                            position1.setLon(position.getLongitudeDeg());
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            logger.error("log Error:  " + throwable.getMessage());
                            latch.countDown();
                        },
                        () -> {
                            logger.info("Successfully downloaded last log!");
                            latch.countDown();
                        });

    }

    public void fetchBattery() {
        CountDownLatch latch = new CountDownLatch(1);
        drone.getTelemetry().getBattery()
                .subscribe(
                        bat -> {
                            logger.debug("battery: " + bat + "%");
                            battery.setVoltageV(bat.getVoltageV());
                            battery.setRemainingPercent(bat.getRemainingPercent());
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            logger.error("log Error:  " + throwable.getMessage());
                            latch.countDown();
                        },
                        () -> {
                            logger.info("Successfully downloaded last log!");
                            latch.countDown();
                        });

    }

    public Position getPosition() {
        return position1;
    }
    public Battery getBattery() { return battery; }
}

