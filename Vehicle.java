package com.company;

public abstract class Vehicle {

    long licence;
    double costFactor;

    public double getCostFactor() {
        return costFactor;
    }
    public abstract VehicleSize size();
}
