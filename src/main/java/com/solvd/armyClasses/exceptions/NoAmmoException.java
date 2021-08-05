package com.solvd.armyClasses.exceptions;

import java.io.IOException;

public class NoAmmoException extends IOException {

    public NoAmmoException(String message){
        super(message);
    }

}
