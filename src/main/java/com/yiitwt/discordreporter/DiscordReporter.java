package com.yiitwt.discordreporter;

import com.yiitwt.discordreporter.handlers.ChatHandler;
import com.yiitwt.discordreporter.handlers.QuitHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import com.yiitwt.discordreporter.handlers.JoinHandler;

public final class DiscordReporter extends JavaPlugin {

        @Override
    public void onEnable() {
        new JoinHandler(this);
        new QuitHandler(this);
        new ChatHandler(this);
        saveDefaultConfig();
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "________  .__                              ._____________                             __                \n");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "\\______ \\ |__| ______ ____  ___________  __| _/\\______   \\ ____ ______   ____________/  |_  ___________ \n");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + " |    |  \\|  |/  ___// __ \\/  _ \\_  __ \\/ __ |  |    |  _// __ \\\\____ \\_/ __ \\_  __ \\   __\\/ __ \\_  __ \\\n");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + " |    `   \\  |\\___ \\\\  ___(  <_> )  | \\/ /_/ |  |    |   \\  ___|  |_> >  ___/|  | \\/|  | \\  ___/|  | \\/\n");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "/_______  /__/____  >\\___  >____/|__|  \\____ |  |______  /\\___  /   __/ \\___  >__|   |__|  \\___  >__|\n");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "        \\/        \\/     \\/                 \\/         \\/     \\/|__|        \\/                 \\/     \n");

            getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "[DiscordReporter] " +ChatColor.RESET+ "is "+ ChatColor.GREEN +"enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "[DiscordReporter] " +ChatColor.RESET+ "is " + ChatColor.DARK_RED +" disabled!");
    }
}
