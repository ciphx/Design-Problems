package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Vehicle vehicle= new Car(23456,0.2);
        Ticket ticket= new Ticket(vehicle);
        ParkingLot parkingLot= new ParkingLot(5,4,11);
        parkingLot.parkVehicle(vehicle);
        System.out.println(ticket.calculateCost(parkingLot));
    }
}
