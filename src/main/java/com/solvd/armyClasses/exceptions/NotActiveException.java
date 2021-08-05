package com.solvd.armyClasses.exceptions;

import java.io.IOException;

public class NotActiveException extends IOException {

    public NotActiveException(String message){
        super(message);
    }

}
