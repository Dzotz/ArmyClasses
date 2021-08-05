package com.solvd.armyClasses.units.land;

import com.solvd.armyClasses.interfaces.IMagazine;
import com.solvd.armyClasses.ammo.Rocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.PriorityQueue;

public class Tank extends LandMachines implements IMagazine {


    private static final Logger LOGGER = LogManager.getLogger(Tank.class.getName());
    private boolean canShoot;
    private final int MAGAZINESIZE = 5;
    private PriorityQueue<Rocket> magazine = new PriorityQueue<>(MAGAZINESIZE);

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

    @Override
    public void fillMagazine(){
        while (magazine.size()<MAGAZINESIZE){
            magazine.add(new Rocket());
        }
    }

    public Tank(boolean active) {
        super(active);
        this.setPower(50);
        this.setCrew(4);
        this.canShoot = true;
        fillMagazine();
    }

    public Tank(boolean active, String date){
        super(active, date);
        this.setPower(50);
        this.setCrew(4);
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
            LOGGER.info("It shoots from tank cannon");
            magazine.poll();
            if (magazine.isEmpty()){
                canShoot = false;
            }
        }

    }

    @Override
    public void reload() {
        int x = MAGAZINESIZE-magazine.size();
        fillMagazine();
        LOGGER.info("It's reloading "+ x + " rockets");
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
        if (o instanceof Tank){
            if (this == o){
                return true;
            }
            Tank object = (Tank) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot&&magazine.size()==object.magazine.size();
        }
        return false;
    }
}
