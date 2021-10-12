package com.thepepeyt.Klasa.databases;

import com.thepepeyt.Klasa.Main;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Database {
    public static Connection con;
    private Main plugin;
    String table = "player";

    public static void connect() {
        File file = new File(((Main)Main.getPlugin(Main.class)).getDataFolder(), "klasa.db");
        if (!file.exists()) {
            new File(((Main)Main.getPlugin(Main.class)).getDataFolder().getPath()).mkdir();
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





    public void disconnect() {
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
            ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS player(UUID String, KLASA String, RASA String, VIP Boolean)");
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + this.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                System.out.println("player found");
                return true;
            }
            System.out.println("player not found");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(UUID uuid) {
        try {
            if (!this.playerExists(uuid)) {
                PreparedStatement insert = con.prepareStatement("INSERT INTO " + this.table + " (UUID,KLASA,RASA,VIP) VALUES (?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, "brak");
                insert.setString(3, "brak");
                insert.setBoolean(4, false);

                insert.executeUpdate();
                System.out.println("player inserted");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKlasa(UUID uuid, String loc) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE " + this.table + " SET KLASA=? WHERE UUID=?");
            statement.setString(1, loc);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRasa(UUID uuid, String loc) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE " + this.table + " SET RASA=? WHERE UUID=?");
            statement.setString(1, loc);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getColumn(UUID uuid, String column) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + this.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            String result = results.getString(column);
            return result;
        }
        catch (SQLException e) {
            String result = null;
            e.printStackTrace();
            return result;
        }
    }

    public void updateVIP(UUID uuid, Boolean loc) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE " + this.table + " SET VIP=? WHERE UUID=?");
            statement.setBoolean(1, loc);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public Boolean getVIP(UUID uuid) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + this.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            Boolean result = results.getBoolean("VIP");
            return result;
        }
        catch (SQLException e) {
            Boolean result = null;
            e.printStackTrace();
            return result;
        }
    }
}
