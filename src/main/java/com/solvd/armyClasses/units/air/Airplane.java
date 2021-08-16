package com.solvd.armyClasses.units.air;

import com.solvd.armyClasses.ammo.Airbomb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class Airplane extends FlyingCombatUnit {

    private static final Logger LOGGER = LogManager.getLogger(Airplane.class.getName());
    private static final int MAXBOMBS = 10;
    private boolean canShoot;

    private HashSet<Airbomb> bombs = new HashSet<>();

    public Airplane (boolean active){
        super(active);
        this.setPower(100);
        this.canShoot=true;
    }

    public Airplane(boolean active, String date){
        super(active, date);
        this.setPower(100);
        this.canShoot=true;
    }

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
            if (!bombs.isEmpty()){
                res += " and drop bombs";
            }
        }
        else{
            res = "I can do nothing";
        }
        LOGGER.info(res);
    }



    @Override
    public void move() {

        LOGGER.info("It moves by air");
    }
    @Override
    public void shoot() {
        LOGGER.info("It shoots with machine gun");
    }

    @Override
    public void reload() {
        LOGGER.info("It reloads");
    }

    public void dropBomb(Airbomb bomb){
        LOGGER.info("boom");
        bombs.remove(bomb);
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
        if (o instanceof Airplane){
            if (this == o){
                return true;
            }
            Airplane object = (Airplane) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot;
        }
        return false;
    }
}
