package com.solvd.armyClasses;

import com.solvd.armyClasses.units.air.Airplane;
import com.solvd.armyClasses.exceptions.NoAmmoException;
import com.solvd.armyClasses.units.air.Helicopter;
import com.solvd.armyClasses.units.land.BTR;
import com.solvd.armyClasses.units.land.Infantry;
import com.solvd.armyClasses.units.land.Tank;
import com.solvd.armyClasses.units.navy.Battleship;
import com.solvd.armyClasses.units.navy.Submarine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //Hash Map with names and armies
        HashMap<String, Army> countries = new HashMap<>();

        //Create three armies
        Army army1 = new Army("05.08.2021");
        Army army2 = new Army("06.08.2020");
        Army army3 = new Army("07.08.2020");

        //Add squads to them and logging
        army1.addSquad(new Squad<>(new Infantry(true), 10));
        army1.addSquad(new Squad<>(new Tank(true), 5));
        LOGGER.info(army1);
        army2.addSquad(new Squad<>(new Helicopter(false), 50));
        army2.addSquad(new Squad<>(new BTR(true, "10.11.12"), 7));
        LOGGER.info(army2);
        army3.addSquad(new Squad<>(new Airplane(false), 50));
        LOGGER.info(army3);

        //Put armies to HashMap
        countries.put("OneRepublic", army1);
        countries.put("NoRepublic", army2);
        countries.put("SleepRepublic", army3);

        //First conflict - without errors, WIN
        try {
            Fight firstFight = new Fight(countries.get("OneRepublic"), countries.get("NoRepublic"), "OneRepublic", "NoRepublic");
            LOGGER.info(firstFight);
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage());
        }

        //Second conflict - CreationDateConflict
        try{
            Fight secondFight = new Fight (countries.get("OneRepublic"), countries.get("NoRepublic"), "OneRepublic", "NoRepublic", "06.08.2022");
            LOGGER.info(secondFight);
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage());
        }

        //Third Conflict - NotActiveException
        try {
            Fight thirdFight = new Fight(countries.get("SleepRepublic"), countries.get("NoRepublic"), "SleepRepublic", "NoRepublic");
            LOGGER.info(thirdFight);
        }
        catch (Exception ex){
            LOGGER.error(ex.getMessage());
        }

        //Submarine can't shoot twice without reloading
        Submarine submarine = new Submarine(true);
        try {
            submarine.shoot();
            submarine.shoot();
        }
        catch (NoAmmoException e){
            LOGGER.error(e.getMessage());
        }

        //Battleship can't shoot if not active
        Battleship battleship = new Battleship(false);
        try{
            battleship.shoot();
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        LOGGER.info(army2.countSquadsWithoutDateOfCreation());
    }
}
