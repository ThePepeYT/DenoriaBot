package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.databases.Database;
import com.thepepeyt.Klasa.databases.WLDatabase;
import com.thepepeyt.Klasa.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class czolgaj implements CommandExecutor, Listener {


    private Main plugin;
    Database db  = new Database();
    Chat cc = new Chat();



    BlockData air = Bukkit.createBlockData("minecraft:air");
    BlockData barrier = Bukkit.createBlockData("minecraft:barrier");

    public czolgaj(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("czolgaj").setExecutor(this);

    }

    Map<UUID, Location> crawlers = new HashMap<UUID, Location>();


    public Boolean isxD(String rasa){
        Boolean is = false;
        if(rasa.equalsIgnoreCase("krasnolud")){
            is = true;
        }
        else if(rasa.equalsIgnoreCase("goblin")){
            is = true;
        }
    return is;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player p = (Player) sender;
        UUID uuid = p.getUniqueId();
        String rasa = db.getColumn(uuid, "rasa");
        if (isxD(rasa)) {
            if (crawlers.containsKey(uuid)) {
                p.sendBlockChange(crawlers.get(uuid), air);
                crawlers.remove(uuid);
                String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Wstałeś na nogi");
                plugin.adventure().player(p).sendMessage(cc.fix(config));


            }
            else{
                Location location = p.getLocation().add(0, 1, 0);
                crawlers.put(uuid, location);
                p.sendBlockChange(location, barrier);
                String config = plugin.getConfig().getString("pomyslnie").replace("{wiadomosc}", "Położyłeś się");
                plugin.adventure().player(p).sendMessage(cc.fix(config));
            }



        }
        else{
            String config = plugin.getConfig().getString("zla-rasa");
            String message = config.replace("{rasa}", "krasnolud lub goblin");
            plugin.adventure().player(p).sendMessage(cc.fix(message));
        }

    return true;
    }


    @EventHandler
    public void crawl(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (crawlers.containsKey(uuid)) {
            Location currentLocation = player.getLocation().add(0, 1, 0);
            Location lastLocation = crawlers.get(uuid);
            if (currentLocation.getBlock() != lastLocation.getBlock()) {
                player.sendBlockChange(crawlers.get(uuid), air);
                player.sendBlockChange(currentLocation, barrier);
                crawlers.put(uuid, currentLocation);
            }
        }
    }

}
