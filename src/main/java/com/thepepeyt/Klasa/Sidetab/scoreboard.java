package com.thepepeyt.Klasa.Sidetab;


import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.utils.Chat;
import fr.mrmicky.fastboard.FastBoard;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;


import java.text.SimpleDateFormat;
import java.util.*;

public class scoreboard implements Listener {


    private Main plugin;
    Chat cc = new Chat();
    Database db = new Database();

    public scoreboard(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    public final Map<UUID, FastBoard> boards = new HashMap<>();




    public void updateBoard(FastBoard board, Player p) {
        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();
        String klasy = db.getColumn(p.getUniqueId(), "klasa");
        String rasy = db.getColumn(p.getUniqueId(), "rasa");
        String xD = x + ", " + y + ", " + z;
        double[] lag = MinecraftServer.getServer().recentTps;
        Long unix_timestamp = p.getPlayerTime();


        int tps1 = (int) lag[0];
        Calendar cal = Calendar.getInstance();

        String d = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
        String[] date = d.split("_");

        String sekundy = String.valueOf(cal.getTime().getSeconds());
        String minuty = String.valueOf(cal.getTime().getMinutes());
        String godzina = String.valueOf(cal.getTime().getHours());


        board.updateLines(
                cc.fixchat("&7&m                                  "),
                cc.fixchat("&c&l>>&3Nick: &b" + p.getName()),
                cc.fixchat("&c&l>>&3Ping: &b " + p.getPing() + " &7ms"),
                cc.fixchat("&c&l>>&3Kordy: &b " + xD),
                "",
                cc.fixchat("&c&l>>&3Profesja: &b " + klasy),
                cc.fixchat("&c&l>>&3Rasa: &b " + rasy),
                cc.fixchat("&c&l>>&3Miasto: &bfajnemiasto"),
                "",
                cc.fixchat("&c&l>>&3TPS: &b " + tps1),
                cc.fixchat("&c&l>>&3Gracze Online: &b " + Bukkit.getServer().getOnlinePlayers().size()),
                cc.fixchat("&c&l>>&3Czas: &b " + godzina + ":" + minuty + ":" + sekundy),
                cc.fixchat("&7&m                                  ")
        );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        board.updateTitle(cc.fixchat("&c&lDENORIA"));

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }



}
