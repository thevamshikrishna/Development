package com.atyeti.dbConfig;

import com.atyeti.exception.UnableToConnectDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DBConnection {
    public static Logger logger=Logger.getLogger(DBConnection.class.getName());
    static  Connection connection;

    public static Connection connectDB(){
        try{
             connection=DriverManager.getConnection(DbConfig.URL,DbConfig.NAME,DbConfig.PASSWORD);
            logger.info("Connection Established");
        }
        catch (Exception e){
            logger.warning("Unable to Connect DB");
            throw new UnableToConnectDataBase("Unable to Connect DataBase Try Again!");
        }
        return connection;
    }
}