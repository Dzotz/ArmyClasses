package com.solvd.armyClasses;

import com.solvd.armyClasses.units.AbstractCombatUnit;
//Generic U will always extend AbstractCombatUnit, so we can use its methods
public class Squad<U extends AbstractCombatUnit> {

    private int numberOfUnits = 0;
    private U unit;

    public Squad(U unitType, int num){
        this.unit = unitType;
        numberOfUnits = num;
    }

    public int GetSquadPower(){
        return unit.getPower()*numberOfUnits;
    }

    public U GetUnit(){
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
            Squad object = (Squad) o;
            if (object.GetUnit().equals(this.GetUnit())&&object.getNumberOfUnits()==this.numberOfUnits) return true;
        }
        return false;
    }

}
