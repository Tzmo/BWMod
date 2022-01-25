package com.bw.mod.util;

import java.io.*;

import com.bw.mod.BWMod;

import net.minecraftforge.common.config.*;

public class Config
{
    public static Configuration config;
    private static String file;
    
    public static void init() {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void removeConfig(final String category) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.hasCategory(category)) {
                Config.config.removeCategory(new ConfigCategory(category));
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void removeConfig(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                Config.config.getCategory(category).remove((Object)key);
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static int getInt(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return Config.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return 0;
    }
    
    public static double getDouble(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return Config.config.get(category, key, 0.0).getDouble();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return 0.0;
    }
    
    public static float getFloat(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return (float)Config.config.get(category, key, 0.0).getDouble();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return 0.0f;
    }
    
    public static String getString(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return Config.config.get(category, key, "").getString();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return "";
    }
    
    public static short getShort(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return (short)Config.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return 0;
    }
    
    public static byte getByte(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return (byte)Config.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return 0;
    }
    
    public static boolean getBoolean(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            if (Config.config.getCategory(category).containsKey(key)) {
                return Config.config.get(category, key, false).getBoolean();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return false;
    }
    
    public static void writeConfig(final String category, final String key, final String value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final String set = Config.config.get(category, key, value).getString();
            Config.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final int value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final int set = Config.config.get(category, key, value).getInt();
            Config.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final boolean value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final boolean set = Config.config.get(category, key, value).getBoolean();
            Config.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final double value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final double set = Config.config.get(category, key, value).getDouble();
            Config.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final short value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final int set = Config.config.get(category, key, (int)value).getInt();
            Config.config.getCategory(category).get(key).set((int)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final byte value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final int set = Config.config.get(category, key, (int)value).getInt();
            Config.config.getCategory(category).get(key).set((int)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final float value) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            final double set = Config.config.get(category, key, (double)value).getDouble();
            Config.config.getCategory(category).get(key).set((double)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
    }
    
    public static boolean hasCategory(final String category) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            return Config.config.hasCategory(category);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return false;
    }
    
    public static boolean hasKey(final String category, final String key) {
        Config.config = new Configuration(new File(Config.file));
        try {
            Config.config.load();
            return Config.config.hasCategory(category) && Config.config.getCategory(category).containsKey(key);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            Config.config.save();
        }
        return false;
    }
    
    public static void setFile(final String filename) {
        Config.file = "config/" + filename;
    }
    
    public String getFile() {
        return Config.file;
    }
    
    static {
        Config.file = "config/" + BWMod.MODID + ".cfg";
    }
}