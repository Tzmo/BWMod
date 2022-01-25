package com.bw.mod;

import com.bw.mod.util.Config;
import com.bw.mod.util.KeyHandler;
import com.bw.mod.util.Stats;
import com.bw.mod.util.commands.GetAPIKey;
import com.bw.mod.util.commands.SetAPIKey;
import com.bw.mod.util.threads.ClearCheckedTimer;
import com.bw.mod.util.threads.GrabStats;
import com.mojang.authlib.GameProfile;
import com.sun.jna.StringArray;
import io.netty.util.internal.ConcurrentSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Mod(modid = "BWMod", name = BWMod.MODID, version = BWMod.VERSION, acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true, canBeDeactivated = true)
public class BWMod {
	public static final String MODID = "BWMod";
	public static final String VERSION = "1.0";
	@Mod.Instance("BWMod")
	public static BWMod instance;
	public static Minecraft mc;
	public static String APIKey = "";
	public static Boolean ChatLinkToggle = false;

	public static IChatComponent PlayerLevelsMsg = new ChatComponentText("");
    public static boolean UpdateLevelQueue = true;
    
    public Map<String, Stats> checked = new HashMap<>();
    public Map<String, Stats> cache = new HashMap<>();

	public static ConcurrentSet<GameProfile> profiles = new ConcurrentSet<>();

	public static KeyBinding GrabStats = new KeyBinding("Grab Stats", Keyboard.KEY_NONE, "BWMod");

	public BWMod() {
        this.mc = Minecraft.getMinecraft();
	}
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File configFile = new File(Loader.instance().getConfigDir(), "BWMod.cfg");
        Configuration config = new Configuration(configFile);

        config.load();
        BWMod.APIKey = Config.getString("bw mod", "API-Key");
        if(config.hasChanged()) {
        	config.save();
        }
    }

	@Mod.EventHandler
	public void init(final FMLInitializationEvent event) throws IOException {
		MinecraftForge.EVENT_BUS.register(this);
		ClientRegistry.registerKeyBinding(GrabStats);
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		ClientCommandHandler.instance.registerCommand(new GetAPIKey());
		ClientCommandHandler.instance.registerCommand(new SetAPIKey());
		new ClearCheckedTimer().start();
	}

    public static BWMod getInstance() {
		return BWMod.instance;
	}
}
