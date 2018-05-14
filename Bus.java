package com.company;

public class Bus extends  Vehicle{

    Bus(long licence,double costFactor){
        super.licence=licence;
        super.costFactor=costFactor;
    }
    public VehicleSize size() {
        return VehicleSize.valueOf("MOTORCYCLE");
    }
}
