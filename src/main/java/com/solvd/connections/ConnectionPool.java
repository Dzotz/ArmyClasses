package com.solvd.connections;

import com.solvd.armyClasses.exceptions.NoAvailableConnections;
import com.solvd.armyClasses.exceptions.NotActiveException;

import java.util.ArrayList;

public class ConnectionPool {
    private static ConnectionPool instance = null;

    private static int MAX_CONNECTIONS = 5;
    private ArrayList<Connection> freeConnections;
    private ArrayList<Connection> usedConnections = new ArrayList<>();


    public ConnectionPool(){
        freeConnections = new ArrayList<>(MAX_CONNECTIONS);
        for (int i = 0; i<MAX_CONNECTIONS; i++){
            freeConnections.add(new Connection());
        }
    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws NoAvailableConnections {
        if(freeConnections.isEmpty()){
            throw new NoAvailableConnections("There is no free connections in connection pool");
        }

        Connection connection = freeConnections.remove(freeConnections.size()-1);

        usedConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection){
        freeConnections.add(connection);
        usedConnections.remove(connection);
    }

    public void shutdown() {
        usedConnections.forEach(this::releaseConnection);
        freeConnections.forEach(c->c.close("connection pool"));
        freeConnections.clear();
    }

    public boolean checkForConnections(){
        return !freeConnections.isEmpty();
    }


}
