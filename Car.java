package com.company;

public class Car extends Vehicle{

    Car(long licence,double costFactor){
        super.licence=licence;
        super.costFactor=costFactor;
    }
    public VehicleSize size() {
        return VehicleSize.valueOf("COMPACT");
    }
}
