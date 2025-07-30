package org.bleachhack.mixin;

import org.bleachhack.BleachHack;
import org.bleachhack.event.events.EventPlayerDeath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DeathScreen;

@Mixin(DeathScreen.class)
public class MixinDeathScreen
{
    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci)
    {
        if (MinecraftClient.getInstance().player != null) {
            var p = MinecraftClient.getInstance().player;
            System.out.printf("You died at: X=%.2f Y=%.2f Z=%.2f%n", p.getX(), p.getY(), p.getZ());

            try {
            	BleachHack.eventBus.post(new EventPlayerDeath(null, p.getX(), p.getY(), p.getZ()));
            } catch (Exception ex) 
            {
            	System.out.println(ex.toString());
            }
        }
    }
}


