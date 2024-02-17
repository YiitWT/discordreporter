package com.yiitwt.discordreporter.handlers;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.yiitwt.discordreporter.DiscordReporter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.EventHandler;



import java.util.Date;

public class ChatHandler implements Listener {
    private final DiscordReporter plugin;

    public ChatHandler(DiscordReporter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        String url =  config.getString("webhook.chatHook");
        WebhookClient client = new WebhookClientBuilder(url).build();
        WebhookMessageBuilder mBuilder = new WebhookMessageBuilder();
        mBuilder.setUsername(player.getName());
        mBuilder.setAvatarUrl("https://mc-heads.net/avatar/" + player.getName());
        mBuilder.setContent(event.getMessage());

        try {
            client.send(mBuilder.build()).thenAccept((message) -> {
                System.out.printf("Message sent: %s%n", message.getId());
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }


    }



}
