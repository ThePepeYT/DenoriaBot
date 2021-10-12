package com.thepepeyt.Klasa.databases;

import com.thepepeyt.Klasa.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class WLDatabase {
    public static Connection con;
    private Main plugin;
    String table = "whitelist";


    public void connect() {
        File file = new File(((Main)Main.getPlugin(Main.class)).getDataFolder(), "wl.db");
        if (!file.exists()) {
            new File(((Main)Main.getPlugin(Main.class)).getDataFolder().getPath()).mkdir();
        }
        String URL2 = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL2);
            createPlayerTable();

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

    public void createPlayerTable() {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS whitelist(NICK String)");
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public boolean isWhitelisted(String name) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `" + table + "` WHERE NICK=?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                ps.close();
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }





    public void dodajwhitelist(String player) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `" + table + "` WHERE NICK=?;");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                final PreparedStatement ps2 = con.prepareStatement("INSERT INTO `" + table + "` (NICK) VALUES (?);");
                ps2.setString(1, player);
                ps2.execute();
                ps2.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }





    public void usunwhtelist(String player) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM `" + table + "` WHERE NICK=?;");
            ps.setString(1, player);
            ps.execute();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }





}
