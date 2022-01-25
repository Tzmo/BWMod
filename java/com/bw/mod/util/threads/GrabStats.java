package com.bw.mod.util.threads;

import com.bw.mod.BWMod;
import com.bw.mod.util.HTTPRequests;
import com.bw.mod.util.Stats;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class GrabStats {

	private static DecimalFormat df1 = new DecimalFormat("#.#");
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public static void searchPlayer(GameProfile profile) {
		new Thread(() -> {

			if (!BWMod.getInstance().checked.containsKey(profile.getName())) {

				if (BWMod.getInstance().cache.containsKey(profile.getName())) {
					BWMod.getInstance().checked.put(profile.getName(), BWMod.getInstance().cache.get(profile.getName()));
					BWMod.profiles.removeIf(x -> x.getName().equalsIgnoreCase(profile.getName()));
					printMessage();
					return;
				}

				JSONObject JSONResponse = null;

				try {
					JSONResponse = (JSONObject) HTTPRequests.GetLevel(profile.getId().toString().replaceAll("-", ""));
				} catch (Exception e) {
					e.printStackTrace();
					BWMod.profiles.removeIf(x -> x.getName().equalsIgnoreCase(profile.getName()));
					printMessage();
					return;
				}

				if (JSONResponse == null) {
					BWMod.profiles.removeIf(x -> x.getName().equalsIgnoreCase(profile.getName()));
					printMessage();
					return;
				}

				try {
					int BWLevel = JSONResponse.getJSONObject("player").getJSONObject("achievements").getInt("bedwars_level");
					int final_kills_bedwars = JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").getInt("final_kills_bedwars");
					int final_deaths_bedwars = JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").getInt("final_deaths_bedwars");
					int wins_bedwars = JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").getInt("wins_bedwars");
					int losses_bedwars = JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").getInt("losses_bedwars");
					double FKDR = (double) final_kills_bedwars / final_deaths_bedwars;
					double WLR = (double) wins_bedwars / losses_bedwars;
					int winstreak = -1;
					if (JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").has("winstreak")) {
						winstreak = JSONResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").getInt("winstreak");
					}
					Stats stats = new Stats(profile.getId(), profile.getName(), WLR, FKDR, BWLevel, winstreak, final_kills_bedwars, wins_bedwars, losses_bedwars);

					BWMod.getInstance().checked.put(profile.getName(), stats);
					BWMod.getInstance().cache.put(profile.getName(), stats);
				}
				catch (JSONException ex) {
					ex.printStackTrace();
					BWMod.profiles.removeIf(x -> x.getName().equalsIgnoreCase(profile.getName()));
					printMessage();
					return;
				}
			}
			BWMod.profiles.removeIf(x -> x.getName().equalsIgnoreCase(profile.getName()));
			ClearCheckedTimer.timer.reset();
			printMessage();
		}).start();
	}

	public static void printMessage() {
		if (BWMod.profiles.isEmpty() && !BWMod.UpdateLevelQueue && !BWMod.getInstance().checked.isEmpty()) {

			BWMod.getInstance().checked.values().stream().sorted(Stats::compareTo).forEach(x -> {
				BWMod.PlayerLevelsMsg.appendSibling(x.toMessage());
				BWMod.PlayerLevelsMsg.appendText("\n");
			});

			if (!BWMod.PlayerLevelsMsg.toString().isEmpty()) {
				EntityPlayer Player = Minecraft.getMinecraft().thePlayer;
				Player.addChatMessage(BWMod.PlayerLevelsMsg);
				BWMod.PlayerLevelsMsg = new ChatComponentText("");
			} else {
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "Could Not Grab Player Levels"));
			}

			BWMod.UpdateLevelQueue = true;
			BWMod.getInstance().checked.clear();
		} else {
			if (BWMod.getInstance().checked.isEmpty() && BWMod.profiles.isEmpty() && !BWMod.UpdateLevelQueue) {
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWLevels" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "Invalid API Key"));
				BWMod.UpdateLevelQueue = true;
			}
		}
	}
}