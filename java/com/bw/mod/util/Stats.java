package com.bw.mod.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.text.DecimalFormat;
import java.util.UUID;

public class Stats implements Comparable<Stats> {

    private static DecimalFormat df1 = new DecimalFormat("#.#");
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public UUID uuid;
    public String name;
    public double fkdr;
    public double wlr;
    public int level;
    public int winstreak;
    public int finals;
    public int wins;
    public int losses;

    public Stats(UUID uuid, String name, double wlr, double fkdr, int level, int winstreak, int finals, int wins, int losses) {
        this.uuid = uuid;
        this.name = name;
        this.wlr = wlr;
        this.fkdr = fkdr;
        this.level = level;
        this.winstreak = winstreak;
        this.finals = finals;
        this.wins = wins;
        this.losses = losses;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() { return name; }

    public double getFkdr() {
        return fkdr;
    }

    public double getWlr() {
        return wlr;
    }

    public int getLevel() {
        return level;
    }

    public int getWinstreak() {
        return winstreak;
    }

    public int getFinals() {
        return finals;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public IChatComponent toMessage() {

        EnumChatFormatting levelColor = (level >= 100) ? EnumChatFormatting.GOLD : EnumChatFormatting.GRAY;
        EnumChatFormatting fkdrColor = (fkdr >= 3) ? EnumChatFormatting.GOLD : EnumChatFormatting.AQUA;

        return new ChatComponentText(
                EnumChatFormatting.AQUA + "[" + levelColor + level + "\u272B" + EnumChatFormatting.AQUA + "] "
                        + EnumChatFormatting.AQUA + name + EnumChatFormatting.DARK_AQUA + " | "
                        + fkdrColor + df2.format(fkdr) + " FKDR" + EnumChatFormatting.DARK_AQUA + " | "
                        + EnumChatFormatting.AQUA + ( (winstreak != -1) ? (winstreak + " WS") : ("[" + EnumChatFormatting.RED + "HIDDEN" + EnumChatFormatting.AQUA + "] WS") ) + EnumChatFormatting.DARK_AQUA + " | "
                        + EnumChatFormatting.AQUA + df2.format(wlr)  + " WLR" + EnumChatFormatting.DARK_AQUA + " | "
                        + EnumChatFormatting.AQUA + finals + " Finals" + EnumChatFormatting.DARK_AQUA + " | "
                        + EnumChatFormatting.AQUA + wins + " Wins" + EnumChatFormatting.DARK_AQUA + " | "
                        + EnumChatFormatting.AQUA + losses + " Losses" + EnumChatFormatting.DARK_AQUA);
    }

    @Override
    public int compareTo(Stats o) {
        return Integer.compare(this.level, o.level);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "uuid=" + uuid +
                ", name=" + name +
                ", fkdr=" + fkdr +
                ", wlr=" + wlr +
                ", level=" + level +
                ", winstreak=" + winstreak +
                ", finals=" + finals +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
