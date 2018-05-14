package com.company;

public class Motorcycle extends Vehicle {

    Motorcycle(long licence, double costFactor){
        super.licence=licence;
        super.costFactor=costFactor;
    }
    public VehicleSize size() {
        return VehicleSize.valueOf("MOTORCYCLE");
    }
}
