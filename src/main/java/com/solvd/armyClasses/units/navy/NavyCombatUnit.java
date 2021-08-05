package com.solvd.armyClasses.units.navy;

import com.solvd.armyClasses.interfaces.ICrew;
import com.solvd.armyClasses.units.AbstractCombatUnit;

public abstract class NavyCombatUnit extends AbstractCombatUnit implements ICrew {

    private int crewNumber = 0;
    public int getCrewNumber(){
        return crewNumber;
    }
    @Override
    public void setCrew(int num){
        crewNumber = num;
    }
    @Override
    public int getCrew(){
        return crewNumber;
    }
    public NavyCombatUnit(boolean active) {
        super(active);
    }
    public NavyCombatUnit(boolean active, String date){ super(active, date); }

}
