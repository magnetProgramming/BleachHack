package org.bleachhack.event.events;

import org.bleachhack.event.Event;

import net.minecraft.entity.damage.DamageSource;

public class EventPlayerDeath extends Event {
    private final double x, y, z;

    public EventPlayerDeath(DamageSource damageSource, double x, double y, double z) 
    {
        this.x = x; this.y = y; this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
}

