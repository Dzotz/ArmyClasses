package com.solvd.armyClasses.units.navy;


import com.solvd.armyClasses.exceptions.NotActiveException;
import com.solvd.armyClasses.interfaces.ICrew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battleship extends NavyCombatUnit implements ICrew {

    private static final Logger LOGGER = LogManager.getLogger(Battleship.class.getName());
    private boolean canShoot;

    public Battleship(boolean active){
        super(active);
        this.setPower(60);
        if(getActive()){
            this.canShoot = true;
        }
        this.setCrew(100);
    }

    public Battleship(boolean active, String date){
        super(active, date);
        this.setPower(60);
        if(getActive()){
            this.canShoot = true;
        }
        this.setCrew(100);
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
        }
        else{
            res = "I can do nothing";
        }
        LOGGER.info(res);
    }


    public void move() {
        LOGGER.info("It moves by water");
    }
    public void shoot() throws NotActiveException {
        if(canShoot){
            LOGGER.info("It shoots with ship cannon");
            canShoot = false;
        }
        else{
            throw new NotActiveException("Battleship is not active");
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
        return res+getPower()+getCrew();
    }

    @Override
    public boolean equals (Object o){
        if (o instanceof Battleship){
            if (this == o){
                return true;
            }
            Battleship object = (Battleship) o;
            return object.getActive() == this.getActive() && object.canShoot == this.canShoot;
        }
        return false;
    }
}
