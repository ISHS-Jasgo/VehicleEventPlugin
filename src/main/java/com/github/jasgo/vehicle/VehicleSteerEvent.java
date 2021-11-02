package com.github.jasgo.vehicle;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VehicleSteerEvent extends Event {

    private final Player player;
    private final Entity vehicle;
    private final boolean isForward;
    private final boolean isBackward;
    private final boolean isLeft;
    private final boolean isRight;
    private final boolean isJump;
    private final boolean isShift;

    public static HandlerList HANDLERS = new HandlerList();

    public VehicleSteerEvent(Player player, Entity vehicle, boolean isForward, boolean isBackward, boolean isLeft, boolean isRight, boolean isJump, boolean isShift) {
        this.player = player;
        this.vehicle = vehicle;
        this.isForward = isForward;
        this.isBackward = isBackward;
        this.isLeft = isLeft;
        this.isRight = isRight;
        this.isJump = isJump;
        this.isShift = isShift;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getVehicle() {
        return vehicle;
    }

    public boolean isForward() {
        return isForward;
    }

    public boolean isBackward() {
        return isBackward;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isJump() {
        return isJump;
    }

    public boolean isShift() {
        return isShift;
    }
}
