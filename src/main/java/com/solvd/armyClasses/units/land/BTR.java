package com.solvd.armyClasses.units.land;


import com.solvd.armyClasses.ammo.BigBullet;
import com.solvd.armyClasses.interfaces.IMagazine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;

public class BTR extends LandMachines implements IMagazine {

    private static final Logger LOGGER = LogManager.getLogger(BTR.class.getName());
    private boolean canShoot;
    private final int MAGAZINESIZE = 500;
    private ArrayDeque<BigBullet> magazine = new ArrayDeque<>(MAGAZINESIZE);


    @Override
    public void allowedCommands() {
        String res;
        if (getActive()){
            res = "I can move and";
            if (canShoot){
                res+=" shoot "+magazine.size()+" rockets";
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

   public BTR(boolean active) {
        super(active);
        this.setPower(30);
        this.setCrew(15);
        this.canShoot = true;
        fillMagazine();
    }

    public BTR(boolean active, String date){
        super(active, date);
        this.setPower(30);
        this.setCrew(15);
        this.canShoot = true;
        fillMagazine();
    }

    @Override
    public void move() {
        LOGGER.info("It moves by land");
    }

    @Override
    public void shoot(){
        if(canShoot){
            LOGGER.info("It shoots from machine gun");
            magazine.pollFirst();
            if (magazine.isEmpty()){
                canShoot = false;
            }
        }
    }



    @Override
    public void reload() {
        int x = MAGAZINESIZE-magazine.size();
        fillMagazine();
        LOGGER.info("It's reloading "+ x + " big bullets");
    }

    @Override
    public void fillMagazine() {
        while (magazine.size()<4){
            magazine.addLast(new BigBullet());
        }
    }

    @Override
    public int hashCode(){
        int res =0;
        if (canShoot) res+=1;
        if (getActive()) res+=1;
        return res+getPower()+getCrew()+ magazine.size();
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof BTR){
            if (this == o){
                return true;
            }
            BTR object = (BTR) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot&&magazine.size()==object.magazine.size();
        }
        return false;
    }
}