package com.solvd.armyClasses;

import com.solvd.armyClasses.units.AbstractCombatUnit;
//Generic U will always extend AbstractCombatUnit, so we can use its methods
public class Squad<U extends AbstractCombatUnit> {

    private int numberOfUnits;
    private U unit;

    public Squad(U unitType, int num){
        this.unit = unitType;
        numberOfUnits = num;
    }

    public Integer getSquadPower(){
        return unit.getPower()*numberOfUnits;
    }

    public U getUnit(){
        return unit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    //Hash is calculated as hash of unit + their quantity
    @Override
    public int hashCode(){
        return unit.hashCode()+numberOfUnits;
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof Squad){
            if (this == o){
                return true;
            }
            Squad<AbstractCombatUnit> object = (Squad<AbstractCombatUnit>) o;
            return object.getUnit().equals(this.getUnit()) && object.getNumberOfUnits() == this.numberOfUnits;
        }
        return false;
    }

}
