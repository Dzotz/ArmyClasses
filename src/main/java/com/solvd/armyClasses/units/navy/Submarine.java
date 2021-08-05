package com.solvd.armyClasses.units.navy;

import com.solvd.armyClasses.interfaces.IYellowSubmarine;
import com.solvd.armyClasses.exceptions.NoAmmoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Submarine extends NavyCombatUnit implements IYellowSubmarine {

    private static final Logger LOGGER = LogManager.getLogger(Submarine.class.getName());
    private boolean canShoot;



    public Submarine(boolean active){
        super(active);
        this.setPower(90);
        this.setCrew(25);
        this.canShoot = true;
    }
    public Submarine(boolean active, String date){
        super(active, date);
        this.setPower(90);
        this.setCrew(25);
        this.canShoot = true;
    }

    @Override
    public void allowedCommands() {
        String res;
        if (getActive()){
            res = "I can move, be a yellow submarine and";
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

    @Override
    public void move() {
        LOGGER.info("It moves under water");
    }

    @Override
    public void shoot() throws NoAmmoException {
        if(canShoot){
            LOGGER.info("It shoots with torpedo");
            canShoot = false;
        }
        else throw new NoAmmoException("Submarine has no ammo");
    }

    @Override
    public void reload(){
        LOGGER.info("It reloads");
        canShoot = true;
    }

    @Override
    public void yellowSubmarine() {
        LOGGER.info("We all live in a yellow submarine");
    }


    @Override
    public int hashCode(){
        int res =0;
        if (canShoot) res+=1;
        if (getActive()) res+=1;
        return res+getPower()+getCrewNumber();
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof Submarine){
            if (this == o){
                return true;
            }
            Submarine object = (Submarine) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot;
        }
        return false;
    }
}
