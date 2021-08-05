package com.solvd.armyClasses.units.land;

import com.solvd.armyClasses.units.AbstractCombatUnit;

public abstract class LandCombatUnit extends AbstractCombatUnit {

    public LandCombatUnit(boolean active) {
        super(active);
    }
    public LandCombatUnit(boolean active, String date){ super(active, date); }


}
