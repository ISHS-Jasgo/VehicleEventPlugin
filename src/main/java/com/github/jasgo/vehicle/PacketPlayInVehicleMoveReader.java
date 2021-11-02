package com.github.jasgo.vehicle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.network.protocol.game.PacketPlayInSteerVehicle;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.List;

public class PacketPlayInVehicleMoveReader {

    private final Player player;
    private int count;


    public PacketPlayInVehicleMoveReader(Player player) {
        this.player = player;
    }
    public boolean inject() {
        CraftPlayer nmsPlayer = (CraftPlayer) player;
        Channel channel = nmsPlayer.getHandle().b.a.k;
        if(channel.pipeline().get("PacketInjector") != null)
            return false;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInSteerVehicle>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, PacketPlayInSteerVehicle packetPlayInSteerVehicle, List<Object> list) throws Exception {
                list.add(packetPlayInSteerVehicle);
                read(packetPlayInSteerVehicle);
            }
        });
        return true;
    }
    private void read(PacketPlayInSteerVehicle packetPlayInSteerVehicle) {
        count++;
        if(count == 6) {
            count = 0;
            boolean left = (float) getValue(packetPlayInSteerVehicle, "c") > 0.0;
            boolean right = (float) getValue(packetPlayInSteerVehicle, "c") < 0.0;
            boolean forward = (float) getValue(packetPlayInSteerVehicle, "d") > 0.0;
            boolean backward = (float) getValue(packetPlayInSteerVehicle, "d") < 0.0;
            boolean jump = (boolean) getValue(packetPlayInSteerVehicle, "e");
            boolean shift = (boolean) getValue(packetPlayInSteerVehicle, "f");
            (new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(new VehicleSteerEvent(player, player.getVehicle(), forward, backward, left, right, jump, shift));
                }
            }).runTask(Vehicle.getPlugin(Vehicle.class));
        }

    }
    private Object getValue(Object instance, String name) {
        Object result = null;
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            result = field.get(instance);
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
