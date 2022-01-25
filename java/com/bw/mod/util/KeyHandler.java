package com.bw.mod.util;

import java.util.Objects;
import java.util.stream.Collectors;

import com.bw.mod.BWMod;
import com.bw.mod.util.threads.GrabStats;
import com.google.common.base.Strings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {

	Timer cdTimer = new Timer();
	public static String PossibleNicks = "";

	@SubscribeEvent
	public void onKeyPressed(KeyInputEvent event) {
		
		if(BWMod.GrabStats.isPressed()) {
			if (cdTimer.hasReached(3000)) {
				handleStatsKey();
				cdTimer.reset();
			} else {
				//3 second cooldown
			}
		}
	}



	public void handleStatsKey() {
		
		if (Minecraft.getMinecraft().theWorld == null) {
			return;
		}

		if (Minecraft.getMinecraft().thePlayer == null) {
			return;
		}

		if (!BWMod.mc.getCurrentServerData().serverIP.matches("^(?:[a-zA-Z0-9]+\\.|)hypixel\\.net$")) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " 
			+ EnumChatFormatting.AQUA + "Not On hypixel.net"));
			return;
		}

		if (!BWMod.APIKey.matches("([0-9a-f]{8})(?:-|)([0-9a-f]{4})(?:-|)(4[0-9a-f]{3})(?:-|)([89ab][0-9a-f]{3})(?:-|)([0-9a-f]{12})")) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "You Need To Set Your API Key. " 
			+ EnumChatFormatting.GRAY + "(" + EnumChatFormatting.AQUA + "do \"/api new\" to get your API Key and \"/SetAPIKey <key>\"" + EnumChatFormatting.GRAY + ")"));
			return;
		}

		if (!BWMod.UpdateLevelQueue) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " 
			+ EnumChatFormatting.AQUA + "Already Attempting To Grab Players Bedwars Stats"));
			return;
		}

		final NetHandlerPlayClient handler = Minecraft.getMinecraft().getNetHandler();
		if (handler == null) {
			return;
		}

		if (handler.getPlayerInfoMap() == null) {
			return;
		}

		BWMod.profiles.clear();
		BWMod.profiles.addAll(Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()
				.stream()
				.map(NetworkPlayerInfo::getGameProfile)
				.filter(Objects::nonNull)
				.filter(p ->
						!Strings.isNullOrEmpty(p.getId().toString())
								&& p.getName().matches("[a-zA-Z0-9_]{1,16}")
								&& p.getId().toString().matches("([0-9a-f]{8})(?:-|)([0-9a-f]{4})(?:-|)(4[0-9a-f]{3})(?:-|)([89ab][0-9a-f]{3})(?:-|)([0-9a-f]{12})"))
				.collect((Collectors.toList())));

		if (BWMod.profiles.size() > 0) {
			BWMod.UpdateLevelQueue = false;
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " 
			+ EnumChatFormatting.AQUA + "Attempting To Grab " + BWMod.profiles.size() + " Players Bedwars Stats"));

			BWMod.profiles.forEach(GrabStats::searchPlayer);
		}
	}
}
