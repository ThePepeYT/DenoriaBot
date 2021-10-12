package com.thepepeyt.Klasa.commands;

import com.thepepeyt.Klasa.Main;
import com.thepepeyt.Klasa.utils.Chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class me implements CommandExecutor {

    Chat cc = new Chat();

    private Main plugin;

    public me(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("me").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage("Podaj co chcesz napisać");
        }else{
            ArmorStand as = player.getWorld().spawn(player.getLocation().add(0, 2.5, 0), ArmorStand.class);
            as.setSmall(true);
            as.setVisible(false);
            as.setGravity(false);
            as.setCustomNameVisible(true);
            as.setCollidable(false);
            as.setMarker(false);
            as.setCustomName(String.join(" ", args));
            player.getPassengers().forEach(Entity::remove);
            player.addPassenger(as);



            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                player.getPassengers().remove(as);

            }, 5 * 20);

            }
        return true;}

}


