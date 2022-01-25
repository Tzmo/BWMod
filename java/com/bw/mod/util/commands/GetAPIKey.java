package com.bw.mod.util.commands;

import java.util.Arrays;
import java.util.List;

import com.bw.mod.BWMod;
import com.bw.mod.util.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GetAPIKey implements ICommand {    
    public String getCommandName() {
        return "GetAPIKey";
    }
    
    public String getCommandUsage(final ICommandSender iCommandSender) {
        return "GetAPIKey";
    }
    
    public List<String> getCommandAliases() {
        final String[] aliases = { "GetAPIKey" };
        return Arrays.asList(aliases);
    }
    
    public void processCommand(final ICommandSender iCommandSender, final String[] strings) throws CommandException {
        if (strings.length == 0) {
        	if (!Config.getString("bw mod", "API-Key").matches("([0-9a-f]{8})(?:-|)([0-9a-f]{4})(?:-|)(4[0-9a-f]{3})(?:-|)([89ab][0-9a-f]{3})(?:-|)([0-9a-f]{12})")) {
        		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWMod" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "You Need To Set Your API Key."));
        	} else {
        		BWMod.APIKey = Config.getString("bw mod", "API-Key");
        		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWMod" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "API Key Set To: " + Config.getString("bw mod", "API-Key")));
        	}
        }
        else {
        	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        	player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "[" + EnumChatFormatting.AQUA + "BWMod" + EnumChatFormatting.BLUE + "] " + EnumChatFormatting.AQUA + "/GetAPIKey"));
        }
    }
    
    public boolean canCommandSenderUseCommand(final ICommandSender iCommandSender) {
        return true;
    }
    
    public List<String> addTabCompletionOptions(final ICommandSender iCommandSender, final String[] strings, final BlockPos blockPos) {
        final String[] aliases = { "GetAPIKey" };
        return Arrays.asList(aliases);
    }
    
    public boolean isUsernameIndex(final String[] strings, final int i) {
        return false;
    }
    
    public int compareTo(final ICommand o) {
        return 0;
    }
}
