package com.company;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import java.util.Map;
import java.util.Vector;


import static com.company.VehicleSize.MOTORCYCLE;

public class ParkingLot {

    Vector<Level> levels= new Vector<Level>();
    MutableMap<Vehicle, Level> vehicle2level= new ConcurrentHashMap<Vehicle, Level>();

    ParkingLot(int numOflevels, int rows, int spotsPerRow) {
        for (int i = 0; i < numOflevels; i++) {
            Level level= new Level(rows,spotsPerRow);
            levels.add(level);
        }
    }


    // Park the vehicle in a spot (or multiple spots)
    // Return false if failed
    boolean parkVehicle(Vehicle vehicle) {
        VehicleSize size = vehicle.size();
        if (size == MOTORCYCLE) {
            System.out.println("Motorcycle" );
            if (!parkInMotoSpot(vehicle)) {
                if (!parkInCompactSpot(vehicle)) {
                    return parkInLargeSpot(vehicle, 1);
                }
            }
            return true;
        } else if (size == VehicleSize.COMPACT) {
            System.out.println("Car" );
            if (!parkInCompactSpot(vehicle)) {
                return parkInLargeSpot(vehicle, 1);
            }
        } else if (size == VehicleSize.LARGE) {
            System.out.println("Bus" );
            return parkInLargeSpot(vehicle, 5);
        }

        return false;
    }

    boolean parkInMotoSpot(Vehicle vehicle) {
        for(int i = 0; i < levels.size(); i++) {
            int index = levels.get(i).canParkVehicle( VehicleSize.MOTORCYCLE, 1);
            if (index != -1) {
                levels.get(i).parkVehicle(vehicle, VehicleSize.MOTORCYCLE, index, 1);
                vehicle2level.put(vehicle,levels.get(i));
                return true;
            }
        }
        return false;
    }

    boolean parkInCompactSpot(Vehicle vehicle) {
        for(int i = 0; i < levels.size(); i++) {
            int index = levels.get(i).canParkVehicle( VehicleSize.COMPACT, 1);
            System.out.println(index );
            if (index != -1) {
                levels.get(i).parkVehicle(vehicle, VehicleSize.COMPACT, index, 1);
                vehicle2level.put(vehicle,levels.get(i));
                return true;
            }
        }
        return false;
    }

    boolean parkInLargeSpot(Vehicle vehicle, int num) {
        for(int i = 0; i < levels.size(); i++) {
            int index = levels.get(i).canParkVehicle( VehicleSize.LARGE, num);
            if (index != -1) {
                levels.get(i).parkVehicle(vehicle, VehicleSize.LARGE, index, num);
                vehicle2level.put(vehicle,levels.get(i));
                return true;
            }
        }
        return false;
    }

    // unPark the vehicle
    void unParkVehicle(Vehicle vehicle) {
        Level level = vehicle2level.get(vehicle);
        if (level!= null) {
            level.unParkVehicle(vehicle);
        }
    }

}
