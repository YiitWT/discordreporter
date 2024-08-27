package com.yiitwt.discordreporter.handlers;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import com.yiitwt.discordreporter.DiscordReporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JoinHandler implements Listener {
    private final DiscordReporter plugin;

    public JoinHandler(DiscordReporter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String ip = "Hidden";

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        String healthString = String.valueOf(Math.floor(player.getHealth()));
        String pingString = String.valueOf(Math.floor(player.getPing()));
        FileConfiguration config = plugin.getConfig();
        String url = config.getString("webhook.joinHook");

        WebhookClient client = new WebhookClientBuilder(url).build();


        try {
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(0x4CFF00)
                    .setTitle(new WebhookEmbed.EmbedTitle(player.getName() + config.get("lang.en.loginMsg"), null))
                    .addField(new WebhookEmbed.EmbedField(true,  config.getString("lang.en.cords"), String.valueOf(Math.floor(player.getLocation().getX())) + " " + String.valueOf(Math.floor(player.getLocation().getY())) + " " + String.valueOf(Math.floor(player.getLocation().getZ()))))
                    .addField(new WebhookEmbed.EmbedField(true,  config.getString("lang.en.worldName"), player.getWorld().getName()))
                    .addField(new WebhookEmbed.EmbedField(true,  config.getString("lang.en.loginDate"), formattedDate))
                    .addField(new WebhookEmbed.EmbedField(false, "IP Address", ip))
                    .addField(new WebhookEmbed.EmbedField(true,  config.getString("lang.en.hp"), healthString))
                   // .addField(new WebhookEmbed.EmbedField(true, "Ping", pingString))
                   // .setFooter(new WebhookEmbed.EmbedFooter("Buy premium version at https://yiitwt.com", "https://i.imgur.com/E5vlyvo.png"))
                   // .setAuthor(new WebhookEmbed.EmbedAuthor("Made by YiitWT with <3", "https://mc-heads.net/avatar/"+player.getName(), "https://yiitwt.com"))
                    .setTimestamp(new Date().toInstant())
                    .build();



            client.send(embed).thenAccept((message) -> {
                System.out.printf("Embed sent: %s%n", message.getId());
            });
        } catch (Exception e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "[DiscordReporter] " +ChatColor.DARK_RED + "Unable to send embed, please check your config or join our discord server.");
        } finally {
            client.close();
        }


    }
}
