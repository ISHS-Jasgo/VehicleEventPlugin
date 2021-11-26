package com.github.jasgo.vehicle;

import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

public class VehicleEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PacketPlayInVehicleMoveReader reader = new PacketPlayInVehicleMoveReader(event.getPlayer());
        reader.inject();
    }

    @EventHandler
    public void onVehicleMove(VehicleSteerEvent event) {
        if (event.getVehicle() instanceof Boat) {
            if (event.isJump()) {
                if (event.getVehicle().getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
                    event.getVehicle().setVelocity(new Vector(0, 0.5, 0));
                }
            }
        }
    }
}
