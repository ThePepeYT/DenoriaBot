package com.thepepeyt.Klasa.databases;

import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RankDatabase {
    public static Connection con;
    private Main plugin;
    String table = "ranking";

    public void connect() {
        File file = new File(((Main)Main.getPlugin(Main.class)).getDataFolder(), "ranking.db");
        if (!file.exists()) {
            new File(((Main)Main.getPlugin(Main.class)).getDataFolder().getPath()).mkdir();
        }
        String URL2 = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL2);
            RankDatabase.createTable();
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
            ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS ranking(UUID String, MOBY INT, DEATHS INT, KILLS INT, WYKOPANE INT, POSTAWIONE INT, OBSIDIAN INT)");
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
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
                PreparedStatement insert = con.prepareStatement("INSERT INTO " + table + " (UUID,MOBY,DEATHS,KILLS,WYKOPANE,POSTAWIONE,OBSIDIAN) VALUES (?,?,?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setInt(2, 0);
                insert.setInt(3, 0);
                insert.setInt(4, 0);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.executeUpdate();
                System.out.println("player inserted");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVar(UUID uuid, String var, int amount) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE " + table + " SET " + var + "=? WHERE UUID=?");
            statement.setInt(1, amount);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVar(UUID uuid, String column) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            String result = results.getString(column);
            return Integer.parseInt(result);
        }
        catch (SQLException e) {
            String result = null;
            e.printStackTrace();
            return Integer.parseInt(result);
        }
    }


    public ArrayList<String> getLeadboard(UUID uuid, String column) {
        try {
            String[] result = new String[5];
            PreparedStatement statement = con.prepareStatement("SELECT UUID " + column +  " FROM " + table + " ORDER BY " + column + " DESC LIMIT 5");
            ResultSet results = statement.executeQuery();


            ArrayList<String> lore = new ArrayList<String>();


            for(int i =1;i<5;i++){
                if(results.next()){
                    String id = results.getString("UUID");
                    int ilosc = results.getInt(column);
                    Player p = Bukkit.getPlayer(id);
                    lore.add(p.getName() + "zdobył " + ilosc);
                }

            }


            return lore;




        }
        catch (SQLException e) {
            ArrayList<String> lore = new ArrayList<String>();
            e.printStackTrace();
            return lore;
        }
    }



    public String[] getTop(String type, int amount) {
        String[] array = new String[amount];
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ranking ORDER BY CONVERT(" + type.toLowerCase() + ", UNSIGNED INTEGER) DESC LIMIT " + amount);
            ResultSet rs = ps.executeQuery();
            int counter = 0;
            while(rs.next()) {
                array[counter] = rs.getString("username") + "." + rs.getInt(type.toLowerCase());
                counter++;
            }
        }catch(SQLException e) {

        }
        return array;
    }




}

