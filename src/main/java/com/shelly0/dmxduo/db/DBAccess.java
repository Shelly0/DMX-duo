package com.shelly0.dmxduo.db;

import java.sql.*;

/**
 * Created by HS on 18.05.2017.
 */
public class DBAccess
{
    private int a;
    private int b;
    private int c;

    public DBAccess()
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c  = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Connection ok");

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS bestand " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    "barcode CHAR(20) NOT NULL," +
                    "bezeichnung TEXT NOT NULL," +
                    "hersteller INT NOT NULL," +
                    "typ INT NOT NULL," +
                    "vorhanden INT NOT NULL," +
                    "zustand INT NOT NULL, " +
                    "entnahmedatum INT NOT NULL, " +
                    "einkaufspreis REAL NOT NULL, " +
                    "kaufdatum INT NOT NULL, " +
                    "gekauftBei INT NOT NULL, " +
                    "notiz TEXT NOT NULL)";
            stmt.executeUpdate(sql);


        /*    stmt.close();
            stmt = c.createStatement();
            sql = "...";
            stmt.executeUpdate(sql); */

            stmt.close();
            c.close();

            System.out.println("Table creation ok");

        } catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new DBAccess();

        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Connection ok");

            Statement stmt = c.createStatement();
            String sql = "SELECT * FROM bestand";
            ResultSet result = stmt.executeQuery(sql);
            while(result.next())
            {
                for(int i=1; i<result.getMetaData().getColumnCount(); ++i)
                {
                    System.out.print(result.getMetaData().getColumnName(i) + " = "  + result.getString(i) + ", ");
                }
                System.out.println();
            }
            stmt.close();
            c.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
