package com.solvd.armyClasses.exceptions;

public class NoAvailableConnections extends RuntimeException {
    public NoAvailableConnections(String message) {
        super(message);
    }
}
