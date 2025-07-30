package org.bleachhack.command.commands;

import java.util.Locale;

import org.bleachhack.command.Command;
import org.bleachhack.command.CommandCategory;
import org.bleachhack.command.exception.CmdSyntaxException;
import org.bleachhack.module.mods.StreamerMode;
import org.bleachhack.util.BleachLogger;

public class CmdStreamerMode extends Command 
{
	

	public CmdStreamerMode() {
		super("streamermode", "Modify Certain Parts Of The Streamer Mode Module.", "streamermode setCoordsBlockMessage COORDSHIDDEN | streamermode detectOBSAndToggleOn", CommandCategory.MISC);
	}

	@Override
	public void onCommand(String alias, String[] args) throws Exception 
	{

	    if (args.length < 1)
	        throw new CmdSyntaxException("Usage: streamermode <subcommand> [args]");

		String param1 = args[0].toLowerCase(Locale.ENGLISH);
		
		if (param1.equals("setcoordsblockmessage")) {
			if (args.length < 2)
				throw new CmdSyntaxException("Usage: streamermode setCoordsBlockMessage <message>");
			
			String message = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
			StreamerMode.INSTANCE.setBlockedCoordsMessage(message);
			BleachLogger.info("Custom coordinates block message set to: " + message);
		} else if (param1.equalsIgnoreCase("detectOBSAndToggleOn"))  
		{
		    boolean enabled = !StreamerMode.INSTANCE.isOBSDetectOnDebugHudEnabled();
		    StreamerMode.INSTANCE.setOBSDetectOnDebugHud(enabled);
		    BleachLogger.info("OBS detection on F3 is now " + (enabled ? "enabled" : "disabled"));
		}
		else {
			throw new CmdSyntaxException("Unknown subcommand: " + param1);
		}
	}
}



