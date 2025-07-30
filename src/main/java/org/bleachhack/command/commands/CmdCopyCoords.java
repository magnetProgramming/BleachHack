package org.bleachhack.command.commands;

import org.bleachhack.command.Command;
import org.bleachhack.command.CommandCategory;
import org.bleachhack.util.BleachLogger;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class CmdCopyCoords extends Command {

	public CmdCopyCoords() 
	{
		super("copycoords", "Copy your current coords to the clipboard.", "copycoords", CommandCategory.MISC);
	}

	@Override
	public void onCommand(String alias, String[] args) throws Exception 
	{
		
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		
		if (player == null) 
			return;
		
		double[] playersCoords = getPlayersCurrentCoords(player);
		
		double xCoords = playersCoords[0];
		double yCoords = playersCoords[1];
		double zCoords = playersCoords[2];
		
		String copiedCoords =
				String.format("%.2f, %.2f, %.2f", xCoords, yCoords, zCoords);
		
		MinecraftClient.getInstance().keyboard.setClipboard(copiedCoords);
		
		BleachLogger.info(String.format("§7Copied: §fX: §a%.2f §fY: §a%.2f §fZ: §a%.2f",
				xCoords, yCoords, zCoords));
	}
	
	private double[] getPlayersCurrentCoords(ClientPlayerEntity player) 
	{
		double xCoords = player.getX();
		double yCoords = player.getY();
		double zCoords = player.getZ();
		
		double[] coords = {xCoords, yCoords, zCoords};
		
		return coords;
	}

}
