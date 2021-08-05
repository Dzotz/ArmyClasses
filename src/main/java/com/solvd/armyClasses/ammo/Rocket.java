package com.solvd.armyClasses.ammo;


public class Rocket implements Comparable<Rocket>{

    private Integer id;

    public  Rocket(){
        this.id = (int)(Math.random()*100);
    }

    public Integer getId(){
        return id;
    }

    @Override
    public int compareTo(Rocket r){
        return id.compareTo(r.getId());
    }
}
