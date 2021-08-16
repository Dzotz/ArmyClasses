package com.solvd.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    Connection() {
    }

    public void open(String threadName) {
        LOGGER.info(threadName+" opened connection");
    }

    public void pingBD(String threadName) {
        LOGGER.info(threadName + " ping");
    }

    public void authBD(String threadName) {
        LOGGER.info(threadName+ " authorizated");
    }

    public void infoBD(String threadName) {
        LOGGER.info("Info: "+ threadName+ " connected");
    }

    public void executeQuery(String threadName) {
        LOGGER.info(threadName+" executed smth dunno");
    }

    public void close(String threadName) {
        LOGGER.info(threadName + " is out");
    }

}