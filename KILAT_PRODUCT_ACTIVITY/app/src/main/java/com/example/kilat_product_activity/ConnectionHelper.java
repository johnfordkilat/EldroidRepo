package com.example.kilat_product_activity;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    static String username;
    static String pass;
    static String ip;
    String port;
    static String database;

    @SuppressLint("NewApi")
    public Connection connectionClass()
    {
        ip = "192.168.0.13";
        database = "ProductDatabase";
        username = "janpord";
        pass = "Wakowakoako1";
        port = "8080";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+username+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch(Exception ex)
        {
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
