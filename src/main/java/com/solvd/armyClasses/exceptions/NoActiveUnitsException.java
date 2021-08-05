package com.solvd.armyClasses.exceptions;

import java.io.IOException;

public class NoActiveUnitsException extends IOException {

    public NoActiveUnitsException(String message) {
        super(message);
    }
}