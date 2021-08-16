package com.solvd.armyClasses.units.air;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Helicopter extends FlyingCombatUnit {

    private static final Logger LOGGER = LogManager.getLogger(Helicopter.class.getName());

    private boolean canShoot = false;

    @Override
    public void allowedCommands() {
        String res;
        if (getActive()) {
            res = "I can move and";
            if (canShoot) {
                res += " shoot";
            } else {
                res += " reload";
            }
        } else {
            res = "I can do nothing";
        }
        LOGGER.info(res);
    }

    public Helicopter(boolean active) {
        super(active);
        this.setPower(90);
    }

    public Helicopter(boolean active, String date) {
        super(active, date);
        this.setPower(90);
    }

    public void move() {
        LOGGER.info("It moves by air");
    }

    public void shoot() {
        LOGGER.info("It shoots with rockets");
    }

    @Override
    public void reload() {
        LOGGER.info("It reloads");
    }

    @Override
    public int hashCode() {
        int res = 0;
        if (canShoot) res += 1;
        if (getActive()) res += 1;
        return res + getPower();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Helicopter) {
            if (this == o) {
                return true;
            }
            Helicopter object = (Helicopter) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot;
        }
        return false;
    }
}
