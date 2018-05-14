package com.company;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Level {

    int numRows, motoSpotSize,compactSpotSize,largeSpotSize;
    Vector<Vehicle> motoSpots= new Vector<Vehicle>();
    Vector<Vehicle> compactSpots= new Vector<Vehicle>();
    Vector<Vehicle> largeSpots= new Vector<Vehicle>();
    Map<Vehicle, Pair<VehicleSize, Integer>> vehicle2spot= new HashMap<Vehicle, Pair<VehicleSize, Integer>>();

    Level(int numRows, int spots_per_row) {
        this.numRows=numRows;
        this.motoSpotSize = spots_per_row / 4; //spots per row
        this.compactSpotSize = spots_per_row * 3 / 4 - motoSpotSize;
        this.largeSpotSize = spots_per_row - spots_per_row * 3 / 4 + 1;
        motoSpots.setSize(motoSpotSize*numRows);
        compactSpots.setSize(compactSpotSize*numRows);
        largeSpots.setSize(largeSpotSize*numRows);
    }

    int canParkVehicle(VehicleSize size, int num) {
        if (size == VehicleSize.MOTORCYCLE) {
            for(int k=0;k<numRows;k++) {
                for (int i = 0; i < motoSpotSize; i++) {
                    if (motoSpots.get(k*numRows+i) == null) {
                        return (k*numRows+i);
                    }
                }
            }
        }
        else if (size == VehicleSize.COMPACT) {
            for(int k=0;k<numRows;k++) {
                for (int i = 0; i < compactSpotSize; i++) {
                    if (compactSpots.get(k*numRows+i) == null) {
                        return (k*numRows+i);
                    }
                }
            }
        }
        else {
            for(int k=0;k<numRows;k++) {
                for (int i = 0; i < largeSpotSize; i++) {
                    int spot=k*numRows+i;
                    if (largeSpots.get(spot) == null) {
                        boolean flag = true;
                        for (int j = spot; j < spot + num; j++) {
                            if (largeSpots.get(spot) != null) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            return spot;
                        }
                    }
                }
            }
        }
        return -1;
    }


    public void parkVehicle(Vehicle vehicle, VehicleSize parkSpotType, int index, int num) {
        if (parkSpotType == VehicleSize.MOTORCYCLE) {
            motoSpots.set(index,vehicle);
        } else if (parkSpotType == VehicleSize.COMPACT) {
            compactSpots.set(index,vehicle);
        } else {
            for(int i = index; i < index + num; i++) {
                largeSpots.set(index,vehicle);
            }
        }
        vehicle2spot.put(vehicle, new Pair<VehicleSize, Integer>(parkSpotType,index));
    }

    public void unParkVehicle(Vehicle vehicle) {
        Pair<VehicleSize, Integer> spot = vehicle2spot.get(vehicle);
        VehicleSize size = spot.getKey(); //which size spot it was parked in, can  be different from its actual size.
        int index = spot.getValue(); //what was the position it was parked in
        if (size == VehicleSize.MOTORCYCLE) {
            motoSpots.set(index,null);// to find which row got free , use index/motoSpotsSize , for column use index%motoSpotsSize
        } else if (size == VehicleSize.COMPACT) {
            compactSpots.set(index,null);
        } else {
            for(int i = index; i < largeSpots.size(); i++) {
                if (largeSpots.get(i) == vehicle) {
                    largeSpots.set(i,null);
                } else {
                    break;
                }
            }
        }

    }
}
