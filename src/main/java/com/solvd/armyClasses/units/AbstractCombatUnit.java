package com.solvd.armyClasses.units;

import com.solvd.armyClasses.interfaces.Moveable;
import com.solvd.armyClasses.interfaces.Shootable;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Date;
//every unit can shoot and move. its army, so no wonder
public abstract class AbstractCombatUnit implements Shootable, Moveable {

    private static final Logger LOGGER = LogManager.getLogger(AbstractCombatUnit.class.getName());

    private int power = 0;
    private boolean isActive;
    private Date dateOfCreation = new Date(0);

    public int getPower(){
        if (isActive)
            return power;
        else
            return 0;
    }
    public void setPower(int p){
        this.power = p;
    }

    public boolean getActive() {
        return isActive;
    }
    public void setActive(boolean active){
        this.isActive = active;
    }

    public Date getDateOfCreation(){
        return dateOfCreation;
    }
    public void setDateOfCreation(String date){
        try{
            this.dateOfCreation = DateUtils.parseDate(date, "dd.MM.yyyy");}
        catch (ParseException ex){
            LOGGER.error(ex.getMessage());
        }
    }

    //every unit will override this method depending on what possibilities thy have
    public abstract void allowedCommands();

    //two constructors, depending on variables
    public AbstractCombatUnit(boolean active){
        this.isActive = active;
    }

    public AbstractCombatUnit(boolean active, String date){
        this.isActive = active;
        try{
            this.dateOfCreation = DateUtils.parseDate(date, "dd.MM.yyyy");}
        catch (ParseException ex){
            LOGGER.error(ex.getMessage());
        }
    }


}
