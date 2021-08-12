package me.hexillium.whisperingshout.commands;

import me.hexillium.whisperingshout.ChatUtil;
import me.hexillium.whisperingshout.Config;
import me.hexillium.whisperingshout.WhisperingShout;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.*;

public class Whisper extends CommandBase {

    public Whisper(){
        aliases = new ArrayList<>();
        Collections.addAll(aliases, "w", "wh");
    }

    private List<String> aliases;

    @Override
    @Nonnull
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    @Nonnull
    public String getName() {
        return "whisper";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/whisper [text?... ...]";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP)){
            sender.sendMessage(new TextComponentString("This command can only be used by an in-game player."));
            return;
        }
        if (!Config.whisper_enabled){
            sender.sendMessage(new TextComponentString("This command has been disabled in the config.").setStyle(new Style().setColor(TextFormatting.RED)));
            return;
        }
        String message = String.join(" ", args);
        if (message.matches("^\\s*$")){
            ChatType pref = WhisperingShout.registerDefault((EntityPlayerMP) sender, ChatType.WHISPER);
            sender.sendMessage(new TextComponentString("You have toggled to chatting in " + pref.toString() + " mode."));
            return;
        }

        ChatUtil.sendWhisper((EntityPlayerMP) sender, new TextComponentString(message), server.getPlayerList().getPlayers());
//        ChatUtil.message((EntityPlayerMP) sender, Config.whisper_range, Config.whisper_squareRange, Config.whisper_check_dimension, Config.whisper_showDistances, ChatUtil.formatMessage("[Whisper] " + sender.getName() + " ", TextFormatting.DARK_GREEN), new TextComponentString(message), server.getPlayerList().getPlayers());
//        server.logInfo("WHISPER [" + Config.whisper_range + "m] <" + sender.getName() + "> " + message);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
