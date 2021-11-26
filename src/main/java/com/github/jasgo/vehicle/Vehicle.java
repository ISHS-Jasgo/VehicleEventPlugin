package com.github.jasgo.vehicle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vehicle extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new VehicleEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
