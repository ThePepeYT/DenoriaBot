package com.thepepeyt.Klasa.discord;

import com.thepepeyt.Klasa.Main;
import java.awt.Color;
import javax.security.auth.login.LoginException;

import com.thepepeyt.Klasa.whitelist.Check;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class Bot
        extends ListenerAdapter
        implements Listener {

    public Main plugin;



    public JDA jda;

    public String token = "ODgyNjMzMTU5NjU1NzE4OTYy.YS-OLw.GyWSinHfn42I36IT1zM62vE0hog";

    public Bot(Main plugin) {
        this.plugin = plugin;

        this.startBot();
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
        this.jda.addEventListener(this);
        this.jda.addEventListener(new Checkwl());
        this.jda.addEventListener(new Gracze());

    }

    private void startBot() {
        try {
            this.jda = JDABuilder.createDefault(this.token).build();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }
    }


    public JDA returnjda(){
        return jda;
    }



    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        TextChannel textChannel = jda.getGuildById("824373754083672124").getTextChannelById(plugin.getConfig().getString("discord-kanal"));
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(p.getName() + " powiedział:", "https://minotar.net/avatar/" + p.getName() + ".png");
        eb.setDescription(message);
        eb.setFooter("Denoria", jda.getGuildById("824373754083672124").getIconUrl());
        eb.setColor(Color.blue);
        textChannel.sendMessage(eb.build()).queue();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        TextChannel textChannel = jda.getGuildById("824373754083672124").getTextChannelById(plugin.getConfig().getString("discord-kanal"));
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(p.getName(), "https://minotar.net/avatar/" + p.getName() + ".png");
        eb.setDescription(p.getName() + " dołączył/a na serwer");
        eb.setFooter("Denoria", jda.getGuildById("824373754083672124").getIconUrl());
        eb.setColor(Color.green);
        textChannel.sendMessage(eb.build()).queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        TextChannel textChannel = jda.getGuildById("824373754083672124").getTextChannelById(plugin.getConfig().getString("discord-kanal"));
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(p.getName(), "https://minotar.net/avatar/" + p.getName() + ".png");
        eb.setDescription(p.getName() + " wyszedł/a z serwera");
        eb.setFooter("Denoria", jda.getGuildById("824373754083672124").getIconUrl());
        eb.setColor(Color.red);
        textChannel.sendMessage(eb.build()).queue();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel textChannel = jda.getGuildById("824373754083672124").getTextChannelById(plugin.getConfig().getString("discord-kanal"));
        if (event.getChannel().equals(textChannel)) {
            if (event.getAuthor().isBot() || event.isWebhookMessage()) {
                return;
            }
            String message = event.getMessage().getContentRaw();
            User user = event.getAuthor();
            String fullname = user.getName() + "#" + user.getDiscriminator();
            Bukkit.broadcastMessage((String)((Object)ChatColor.AQUA + fullname + ": " + (Object)ChatColor.GREEN + message));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        TextChannel textChannel = jda.getGuildById("824373754083672124").getTextChannelById(plugin.getConfig().getString("discord-kanal"));
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(p.getName(), "https://minotar.net/avatar/" + p.getName() + ".png");
        eb.setDescription("```" + e.getDeathMessage() + "```");
        eb.setFooter("Denoria", jda.getGuildById("824373754083672124").getIconUrl());
        eb.setColor(Color.black);
        textChannel.sendMessage(eb.build()).queue();


    }

}
