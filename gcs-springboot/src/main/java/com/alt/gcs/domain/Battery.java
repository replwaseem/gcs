package com.alt.gcs.domain;

public class Battery {
    private Float voltageV;
    private Float remainingPercent;

    public Float getVoltageV() {
        return voltageV;
    }

    public void setVoltageV(Float voltageV) {
        this.voltageV = voltageV;
    }

    public Float getRemainingPercent() {
        return remainingPercent;
    }

    public void setRemainingPercent(Float remainingPercent) {
        this.remainingPercent = remainingPercent;
    }
}
