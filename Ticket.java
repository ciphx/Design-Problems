package com.company;

public class Ticket {

    private long startTime ;
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        super();
        this.startTime= System.currentTimeMillis();
        this.vehicle = vehicle;
    }
    public long calcualteParkingDuration(){
        return System.currentTimeMillis() - startTime;
    }
    public double calculateCost(ParkingLot parkingLot){
        parkingLot.unParkVehicle(vehicle);
        return (calcualteParkingDuration()*vehicle.getCostFactor());
    }


}
