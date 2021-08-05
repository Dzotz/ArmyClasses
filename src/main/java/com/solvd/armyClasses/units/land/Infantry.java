package com.solvd.armyClasses.units.land;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Infantry extends LandCombatUnit {

    private static final Logger LOGGER = LogManager.getLogger(Infantry.class.getName());
    private boolean canShoot;

    @Override
    public void allowedCommands() {
        String res;
        if (getActive()){
            res = "I can move and";
            if (canShoot){
                res+=" shoot";
            }
            else{
                res+=" reload";
            }
        }
        else{
            res = "I can do nothing";
        }
        LOGGER.info(res);
    }

    public Infantry(boolean active) {
        super(active);
        this.setPower(1);
        this.canShoot = true;
    }

    public Infantry(boolean active, String date){
        super(active, date);
        this.setPower(1);
        this.canShoot = true;
    }

    @Override
    public void move() {
        LOGGER.info("It moves by land");
    }

    @Override
    public void shoot(){
        if(canShoot){
            LOGGER.info("It shoots with rifle");
            canShoot = false;
        }
    }

    @Override
    public void reload() {
        LOGGER.info("It reloads");
        canShoot = true;
    }

    @Override
    public int hashCode(){
        int res =0;
        if (canShoot) res+=1;
        if (getActive()) res+=1;
        return res+getPower();
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof Infantry){
            if (this == o){
                return true;
            }
            Infantry object = (Infantry) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot;
        }
        return false;
    }
}
