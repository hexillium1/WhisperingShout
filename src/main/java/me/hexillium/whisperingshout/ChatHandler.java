package me.hexillium.whisperingshout;

import me.hexillium.whisperingshout.commands.ChatType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ChatHandler {

    private static MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.SERVER)
    public static void onChat(ServerChatEvent event) {
        if (!Config.chat_enabled) return;
        ChatType pref = WhisperingShout.getChatPreference(event.getPlayer());
        switch (pref) {

            case OUT_OF_CHARACTER:
                ChatUtil.sendOOC(event.getPlayer(), new TextComponentString(event.getMessage()), server.getPlayerList().getPlayers());
                break;
            case SHOUT:
                ChatUtil.sendShout(event.getPlayer(), new TextComponentString(event.getMessage()), server.getPlayerList().getPlayers());
                break;
            case WHISPER:
                ChatUtil.sendWhisper(event.getPlayer(), new TextComponentString(event.getMessage()), server.getPlayerList().getPlayers());
                break;
            case TALK:
                ChatUtil.sendTalk(event.getPlayer(), new TextComponentString(event.getMessage()), server.getPlayerList().getPlayers());
                break;
            case UNSPECIFIED:
            default:
                ChatUtil.message(event.getPlayer(), Config.chat_range, Config.chat_squareRange, Config.chat_check_dimension, Config.chat_showDistances,
                        new TextComponentString(""), event.getComponent(), server.getPlayerList().getPlayers());
                server.logInfo("CHAT [" + Config.chat_range + "m] <" + event.getPlayer().getName() + "> " + event.getMessage());
                break;
        }
//        ChatUtil.message(event.getPlayer(), Config.chat_range, Config.chat_squareRange, Config.chat_check_dimension, Config.chat_showDistances,
//                new TextComponentString(""), event.getComponent(), server.getPlayerList().getPlayers());
        event.setCanceled(true);
    }


}
