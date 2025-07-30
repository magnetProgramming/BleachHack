package org.bleachhack.module.mods;

import org.bleachhack.event.events.EventPlayerDeath;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.SettingMode;
import org.bleachhack.util.BleachLogger;

import net.minecraft.client.MinecraftClient;

public class DeathCoordinates extends Module {

    public DeathCoordinates() {
        super("DeathCoordinates", KEY_UNBOUND, ModuleCategory.PLAYER,
              "Displays the coordinates of your last death location.",
              new SettingMode("Mode", "Notify from chat", "Copy to clipboard", "Both")
                  .withDesc("How death coordinates are shown."));
    }

    @BleachSubscribe
    public void onDeath(EventPlayerDeath event) {
        String copiedCoords = String.format("%.2f, %.2f, %.2f", event.getX(), event.getY(), event.getZ());
        int mode = getSetting(0).asMode().getMode();

        if (mode == 0) {
            BleachLogger.info(String.format(
            	    "§7You Died At: §fX: §a%.2f §fY: §a%.2f §fZ: §a%.2f",
            	    event.getX(), event.getY(), event.getZ()));
        } else if (mode == 1) {
            MinecraftClient.getInstance().keyboard.setClipboard(copiedCoords);
        } else if (mode == 2) {
            BleachLogger.info(String.format(
            	    "§7You Died At: §fX: §a%.2f §fY: §a%.2f §fZ: §a%.2f",
            	    event.getX(), event.getY(), event.getZ()));
            MinecraftClient.getInstance().keyboard.setClipboard(copiedCoords);
        }
    }
}
