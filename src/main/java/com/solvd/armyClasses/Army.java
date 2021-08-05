package com.solvd.armyClasses;

import com.solvd.armyClasses.units.AbstractCombatUnit;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Army {

    private static final Logger LOGGER = LogManager.getLogger(Army.class.getName());
    //array of squads
    private ArrayList<Squad<AbstractCombatUnit>> squads = new ArrayList<>();
    private Date dateOfCreation = new Date(0);

    public Army(String date){
        try{
            this.dateOfCreation = DateUtils.parseDate(date, "dd.MM.yyyy");}
        catch (ParseException ex){
            LOGGER.error(ex.getMessage());
        }
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

    public void AddSquad (Squad<AbstractCombatUnit> squad){
        this.squads.add(squad);
    }

    //get info about squad in string by it's id if exists
    public String GetSquadInfoById(int id){
        if (id<squads.size()){
            return squads.get(id).GetUnit().getClass().getSimpleName()+" "+squads.get(id).GetUnit().getPower();
        }else return "Nothing found";
    }

    public Squad<AbstractCombatUnit> GetSquadById(int id){
        if (id<squads.size()){
            return squads.get(id);
        }
        else return null;
    }

    //Check if army have any active squads
    public boolean CheckForActiveSquads(){
        for(Squad<AbstractCombatUnit> squad : squads){
            if (squad.GetUnit().getActive()) return true;
        }
        return false;
    }

    public void DeleteSquadById (int id){
        if (id<squads.size()){
            squads.remove(id);
        }
    }

    //counts power of all active squads
    public int GetArmyPower(){
        int result = 0;
        for (Squad<AbstractCombatUnit> squad:squads) {
            result += squad.GetSquadPower();
        }
        return result;
    }



    @Override
    public String toString(){
        return "This army have " + squads.size() + " squads";
    }

    //Hash of army is sum of hash of all squads in it with hash of creation date
    @Override
    public int hashCode(){
        int res = 0;
        for (Squad<AbstractCombatUnit> squad:squads){
            res+=squad.hashCode();
        }
        return res+dateOfCreation.hashCode();
    }
}
