package com.solvd.armyClasses;

import com.solvd.armyClasses.exceptions.CreationDateConflict;
import com.solvd.armyClasses.exceptions.NoActiveUnitsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

//enum represents possible battle results


public class Fight {
    private static final Logger LOGGER = LogManager.getLogger(Fight.class.getName());

    enum FightResult{
        WIN,
        LOSS,
        TIE
    }

    private Date dateOfFight = new Date(0);
    //armies that take part in conflict
    private Army attacker, defender;
    //names of countries
    public String attackerCountry, defenderCountry;
    private FightResult fightResult;

    public void setDateOfFight(String date) throws CreationDateConflict {
        try{
            this.dateOfFight = DateUtils.parseDate(date, "dd.MM.yyyy");
            if (attacker.getDateOfCreation().before(dateOfFight)||defender.getDateOfCreation().before(dateOfFight)){
                dateOfFight = new Date(0);
                throw new CreationDateConflict("Armies were created after conflict has started");
            }
        }
        catch (ParseException ex){
            LOGGER.error(ex.getMessage());
        }
    }

    public Date getDateOfFight(){
        return dateOfFight;
    }

    public FightResult getFightResult(){
        return fightResult;
    }

    //Two possible constructors depending on variables
    public Fight (Army army1, Army army2, String country1, String country2, String date) throws NoActiveUnitsException, CreationDateConflict{
        if (!army1.checkForActiveSquads()) throw new NoActiveUnitsException("There is no active units from attacking side");
        this.attacker = army1;
        this.defender = army2;
        this.fightResult = predictFightResult();
        this.attackerCountry = country1;
        this.defenderCountry = country2;
        setDateOfFight(date);
    }

    public Fight (Army army1, Army army2, String country1, String country2) throws NoActiveUnitsException {
        if (!army1.checkForActiveSquads()) throw new NoActiveUnitsException("There is no active units from attacking side");
        this.attacker = army1;
        this.defender = army2;
        this.fightResult = predictFightResult();
        this.attackerCountry = country1;
        this.defenderCountry = country2;
    }

    //function that check what will be in result of conflict
    private FightResult predictFightResult(){
        int res = attacker.getArmyPower() - defender.getArmyPower();
        if (res>0){
            LOGGER.info("Win");
            return FightResult.WIN;
        }
        else if (res == 0){
            LOGGER.info("Tie");
            return FightResult.TIE;
        }
        else{
            LOGGER.info("Loss");
            return FightResult.LOSS;
        }
    }


    @Override
    public String toString(){
        String res = "";
        if (DateUtils.isSameDay(dateOfFight, new Date(0))){
            res += "Someday there was a conflict between "+attackerCountry+" and "+defenderCountry;
        }
        else{
            res+="At "+dateOfFight.toString()+" there was a conflict between "+attackerCountry+" and "+defenderCountry;
        }
        switch (fightResult){
            case WIN: res+=" and "+attackerCountry+" won"; break;
            case TIE: res+=" and it was a tie"; break;
            case LOSS: res+=" and "+defenderCountry+" won"; break;
        }
        return res;
    }

}
