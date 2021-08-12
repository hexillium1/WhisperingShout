package me.hexillium.whisperingshout;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ChatUtil {

    public static void sendOOC(EntityPlayerMP fromPlayer, ITextComponent message, List<EntityPlayerMP> players) {
        ChatUtil.sendAll(fromPlayer,
                ChatUtil.formatMessage("[OOC] " + fromPlayer.getName() + " ", TextFormatting.GRAY),
                message,
                players);
        fromPlayer.getServer().logInfo("OUTOFCHARACTER [global] <" + fromPlayer.getName() + "> " + message.getUnformattedText());
    }

    public static void sendShout(EntityPlayerMP fromPlayer, ITextComponent message, List<EntityPlayerMP> players) {
        ChatUtil.message(fromPlayer, Config.shout_range, Config.shout_squareRange, Config.shout_check_dimension,
                Config.shout_showDistances, ChatUtil.formatMessage("[Shout] " + fromPlayer.getName() + " ", TextFormatting.YELLOW),
                message, players);
        fromPlayer.getServer().logInfo("SHOUT [" + Config.shout_range + "m] <" + fromPlayer.getName() + "> " + message.getUnformattedText());
    }

    public static void sendTalk(EntityPlayerMP fromPlayer, ITextComponent message, List<EntityPlayerMP> players) {
        ChatUtil.message(fromPlayer, Config.talk_range, Config.talk_squareRange, Config.talk_check_dimension,
                Config.talk_showDistances, ChatUtil.formatMessage("[Talk] " + fromPlayer.getName() + " ", TextFormatting.GOLD),
                message, players);
        fromPlayer.getServer().logInfo("TALK [" + Config.talk_range + "m] <" + fromPlayer.getName() + "> " + message.getUnformattedText());
    }

    public static void sendWhisper(EntityPlayerMP fromPlayer, ITextComponent message, List<EntityPlayerMP> players) {
        ChatUtil.message(fromPlayer, Config.whisper_range, Config.whisper_squareRange, Config.whisper_check_dimension,
                Config.whisper_showDistances, ChatUtil.formatMessage("[Whisper] " + fromPlayer.getName() + " ", TextFormatting.DARK_GREEN),
                message, players);
        fromPlayer.getServer().logInfo("WHISPER [" + Config.whisper_range + "m] <" + fromPlayer.getName() + "> " + message.getUnformattedText());
    }

    public static void message(EntityPlayerMP fromPlayer, double range, boolean square_mode, boolean checkDim, boolean showDist, ITextComponent prefix, ITextComponent message, List<EntityPlayerMP> players) {

        ITextComponent reset = new TextComponentString("").setStyle(new Style().setColor(TextFormatting.RESET));

        ITextComponent itc = reset.appendSibling(message);
        for (EntityPlayerMP player : players) {
            if (checkDim && player.dimension != fromPlayer.dimension) continue;
            double dist = getDistance(fromPlayer, player, square_mode);
            if (dist <= range) {
                ITextComponent base = showDist ? new TextComponentString("[" + (Math.round(dist * 10) / 10d) + "m] ").setStyle(new Style().setColor(TextFormatting.AQUA)) : new TextComponentString("");
                player.sendMessage(prefix.createCopy().appendSibling(base.appendSibling(itc)));
            }
        }

    }

    public static void sendAll(EntityPlayerMP fromPlayer, ITextComponent prefix, ITextComponent message, List<EntityPlayerMP> players) {
        ITextComponent reset = new TextComponentString("").setStyle(new Style().setColor(TextFormatting.RESET));
        prefix.appendSibling(reset.createCopy());
        ITextComponent itc = reset.appendSibling(message);
        for (EntityPlayerMP player : players) {
            player.sendMessage(prefix.createCopy().appendSibling(itc));
        }
    }

    private static double getDistance(EntityPlayerMP player1, EntityPlayerMP player2, boolean square) {
        if (square) {
            return Math.abs(player1.posX - player2.posX) + Math.abs(player1.posY - player2.posY) + Math.abs(player1.posZ - player2.posZ);
        } else {
            return player1.getDistance(player2);
        }
    }

    public static ITextComponent formatMessage(String message, TextFormatting tf) {
        return (new TextComponentString(message).setStyle(new Style().setColor(tf)));
    }

    public static ITextComponent join(ITextComponent itc1, ITextComponent itc2) {
        return itc1.createCopy().appendSibling(itc2.createCopy());
    }


}
