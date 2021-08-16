package com.solvd.connections;

import com.solvd.armyClasses.exceptions.NoAvailableConnections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public class ConnectionsMain {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionsMain.class);
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>();


        LOGGER.info("Main thread started...");
        Runnable r = ()->{
            LOGGER.info("started... \n");
            Random random = new Random();
            try{
                Thread.sleep(random.nextInt(800)+200);
                Connection connection = connectionPool.getConnection();
                connection.open(Thread.currentThread().getName());
                connection.authBD(Thread.currentThread().getName());
                connection.pingBD(Thread.currentThread().getName());
                connection.infoBD(Thread.currentThread().getName());
                connection.executeQuery(Thread.currentThread().getName());
                connection.close(Thread.currentThread().getName());
                Thread.sleep(random.nextInt(800)+200);
                connectionPool.releaseConnection(connection);
            }
            catch(InterruptedException e){
                LOGGER.error("Thread has been interrupted");
            }
            catch (NoAvailableConnections e){
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("finished... \n");
        };
        for (int i=0; i<8;i++){
            threads.add(new Thread(r, "thread "+ i));
            threads.get(i).start();
            /*try{
                threads.get(i).join();
            }
            catch (InterruptedException e){
                LOGGER.error("thread has been interrupted " +threads.get(i).getName());
            }*/
        }
        LOGGER.info("Main thread finished...");

    }

}
