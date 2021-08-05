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

    public void addSquad(Squad<AbstractCombatUnit> squad){
        this.squads.add(squad);
    }

    //get info about squad in string by it's id if exists
    public String getSquadInfoById(int id){
        if (id<squads.size()){
            return squads.get(id).getUnit().getClass().getSimpleName()+" "+squads.get(id).getUnit().getPower();
        }else return "Nothing found";
    }

    public Squad<AbstractCombatUnit> GetSquadById(int id){
        if (id<squads.size()){
            return squads.get(id);
        }
        else return null;
    }

    //Check if army have any active squads
    public boolean checkForActiveSquads(){

         /*for(Squad<AbstractCombatUnit> squad : squads){
            if (squad.GetUnit().getActive()) return true;
        }*/

        return squads.stream().anyMatch(s -> s.getUnit().getActive());
    }

    public void deleteSquadById(int id){
        if (id<squads.size()){
            squads.remove(id);
        }
    }

    //counts power of all active squads
    public int getArmyPower(){
        return squads.stream().mapToInt(Squad::getSquadPower).sum();
    }

    public long countSquadsWithoutDateOfCreation(){
        return squads.stream().filter(s->s.getUnit().getDateOfCreation().equals(new Date(0))).count();
    }

    @Override
    public String toString(){
        return "This army have " + squads.size() + " squads";
    }

    //Hash of army is sum of hash of all squads in it with hash of creation date
    @Override
    public int hashCode(){
        int res = squads.stream().mapToInt(Squad::hashCode).sum();
        return res+dateOfCreation.hashCode();
    }
}
