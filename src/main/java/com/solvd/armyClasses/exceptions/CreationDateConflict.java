package com.solvd.armyClasses.exceptions;

import java.io.IOException;

public class CreationDateConflict  extends IOException {
    public CreationDateConflict(String message) {
        super(message);
    }
}
