package com.shelly0.dmxduo.db;

import java.sql.*;

/**
 * Created by HS on 18.05.2017.
 */
public class DBAccess
{

    public DBAccess()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Connection ok");

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS barcodes" +
                    "(barcode INT PRIMARY KEY NOT NULL," +
                    "items_itemID INT NOT NULL," +
                    "lastremoval DATE NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS items" +
                    "(itemID INT PRIMARY KEY NOT NULL," +
                    "title TEXT NOT NULL," +
                    "manufacturers_manufacturerID INT NOT NULL," +
                    "typs_typID INT NOT NULL," +
                    "status_statusID INT NOT NULL," +
                    "purchasedetails_purchaseID INT NOT NULL," +
                    "note TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS typs" +
                    "(typID INT PRIMARY KEY NOT NULL," +
                    "typ TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS manufacturers" +
                    "(manufacturerID INT PRIMARY KEY NOT NULL," +
                    "manufacturer TEXT NOT NULL," +
                    "note TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS reparings" +
                    "(reparingID INT PRIMARY KEY NOT NULL," +
                    "items_itemID INT NOT NULL," +
                    "date INT NOT NULL," +
                    "costs REAL NOT NULL," +
                    "reparer TEXT NOT NULL," +
                    "note TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS purchase_details" +
                    "(purchaseID INT PRIMARY KEY NOT NULL," +
                    "price REAL NOT NULL, " +
                    "dateofpurchase DATE NOT NULL, " +
                    "boughtat TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS status" +
                    "(statusID INT PRIMARY KEY NOT NULL," +
                    "status boolean NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();


            c.close();

            System.out.println("Table creation ok");

        } catch (ClassNotFoundException | SQLException e)
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
            String sql;
            sql = "SELECT * FROM items, manufacturers, purchase_details, reparings, status, typs, barcodes";


            ResultSet result = stmt.executeQuery(sql);
            while (result.next())
            {
                for (int i = 1; i < result.getMetaData().getColumnCount(); ++i)
                {
                    System.out.print(result.getMetaData().getColumnName(i) + " = " + result.getString(i) + ", ");
                }
                System.out.println();
            }
            stmt.close();

            c.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
