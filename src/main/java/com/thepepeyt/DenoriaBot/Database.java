package com.thepepeyt.DenoriaBot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class Database {
    public static Connection con;

    String table = "player";

    public static void connect() throws IOException {





        File file = new File("database.db");
        if(!file.exists()){
            file.createNewFile();
        }else{
            System.out.println("File already exists");
        }

        String URL2 = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL2);
            Database.createTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void disconnect() {
        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS player(UUID String, MONEY INT, BANK INT)");
            ps.execute();
            ps.close();
            System.out.println("Table stworzony");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean playerExists(String uuid) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM player WHERE UUID=?");
            statement.setString(1, uuid);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                System.out.println("jest");
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("nie jest");
        return false;
    }

    public static void createPlayer(String uuid) {
        try {
            if (!playerExists(uuid)) {
                PreparedStatement insert = con.prepareStatement("INSERT INTO player (UUID,MONEY,BANK) VALUES (?,?,?)");
                insert.setString(1, uuid);
                insert.setInt(2, 0);
                insert.setInt(3, 0);
                insert.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateColumn(String uuid, String column, int cash) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE player SET " + column + "=? WHERE UUID=?");
            statement.setInt(1, cash);
            statement.setString(2, uuid);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static int getColumn(String uuid, String column) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM player WHERE UUID=?");
            statement.setString(1, uuid);
            ResultSet results = statement.executeQuery();
            results.next();
            int result = results.getInt(column);
            return result;
        }
        catch (SQLException e) {
            int result = 0;
            e.printStackTrace();
            return result;
        }
    }

}
