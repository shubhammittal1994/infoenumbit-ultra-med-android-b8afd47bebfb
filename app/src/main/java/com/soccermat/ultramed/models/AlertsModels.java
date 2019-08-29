package com.soccermat.ultramed.models;

public class AlertsModels {

    String startTime;
    String frequency;
    long strMiloSecds;

    public AlertsModels(String startTime, String frequency, long strMiloSecds) {
        this.startTime = startTime;
        this.frequency = frequency;
        this.strMiloSecds = strMiloSecds;
    }

    public String getStartTime() {
        return startTime;
    }

    public long getStrMiloSecds() {
        return strMiloSecds;
    }

    public String getFrequency() {
        return frequency;
    }
}
