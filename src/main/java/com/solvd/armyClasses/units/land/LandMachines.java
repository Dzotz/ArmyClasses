package com.solvd.armyClasses.units.land;

import com.solvd.armyClasses.interfaces.ICrew;

public abstract class LandMachines extends LandCombatUnit implements ICrew {

    public LandMachines(boolean active) {
        super(active);
    }
    public LandMachines(boolean active, String date){ super(active, date); }


    private int crewNumber;

    public int getCrew(){
        return crewNumber;
    }
    public void setCrew(int num){
        this.crewNumber = num;
    }
}
