package com.solvd.armyClasses.units.air;


import com.solvd.armyClasses.units.AbstractCombatUnit;

public abstract class FlyingCombatUnit extends AbstractCombatUnit {


    public FlyingCombatUnit(boolean active) {
        super(active);
    }
    public FlyingCombatUnit(boolean active, String date){ super(active, date); }


}
