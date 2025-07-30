package org.bleachhack.module.mods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bleachhack.BleachHack;
import org.bleachhack.event.events.EventDebugHud;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.ModuleSetting;
import org.bleachhack.setting.module.SettingMode;
import org.bleachhack.util.BleachLogger;

public final class StreamerMode extends Module 
{
	
	public static StreamerMode INSTANCE;
	
	private boolean obsDetectOnDebugHud = false;

	public void enableOBSDetectOnDebugHud() {
	    obsDetectOnDebugHud = true;
	}

	public boolean isOBSDetectOnDebugHudEnabled() {
	    return obsDetectOnDebugHud;
	}
	
	public void setOBSDetectOnDebugHud(boolean enabled) {
	    this.obsDetectOnDebugHud = enabled;
	}

	
	public String getBlockedCoordsMessage() {
		return blockedCoordsMessage;
	}

	public void setBlockedCoordsMessage(String blockedCoordsMessage) {
		this.blockedCoordsMessage = blockedCoordsMessage;
	}

	public String blockedCoordsMessage = "XYZ: BLOCKED FROM STREAMER MODE :)";

	public StreamerMode() 
	{
		super("StreamerMode", KEY_UNBOUND, ModuleCategory.MISC,
	              "Module for streamers.",
	              new SettingMode("Mode", "", "", "")
	                  .withDesc(""));
		
		INSTANCE = this;
		
		BleachHack.eventBus.subscribe(this);
	}
	
	@BleachSubscribe
	public void onDebugHud(EventDebugHud event) {
	    if (obsDetectOnDebugHud && !this.isEnabled() && isOBSRunning()) {
	        this.setEnabled(true);
	        BleachLogger.info("OBS detected during debug screen — Streamer Mode enabled.");
	        obsDetectOnDebugHud = false; 
	    }
	}
	
	@BleachSubscribe
    public void onGetLeftText(EventDebugHud event) 
	{
		if (!this.isEnabled()) 
			return;
		
        for (int i = 0; i < event.lines.size(); i++) {
            String line = event.lines.get(i);

            if (line.startsWith("XYZ:")) {
                event.lines.set(i, blockedCoordsMessage); // XYZ: BLOCKED FROM STREAMER MODE :)
            }
        }
	}
	
	public boolean isOBSRunning() {
	    try {
	        Process process = Runtime.getRuntime().exec("tasklist");
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (line.toLowerCase().contains("obs64.exe") || line.toLowerCase().contains("obs.exe")) {
	                    return true;
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}
