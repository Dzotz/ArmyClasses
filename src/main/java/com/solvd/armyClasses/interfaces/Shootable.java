package com.solvd.armyClasses.interfaces;

import com.solvd.armyClasses.exceptions.NoAmmoException;
import com.solvd.armyClasses.exceptions.NotActiveException;

public interface Shootable {
    void shoot() throws NoAmmoException, NotActiveException;
    void reload();
}
