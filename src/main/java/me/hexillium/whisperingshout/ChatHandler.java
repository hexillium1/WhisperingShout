package me.hexillium.whisperingshout;

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
        ChatUtil.message(event.getPlayer(), Config.chat_range, Config.chat_squareRange, Config.chat_check_dimension, Config.chat_showDistances, new TextComponentString(""), event.getComponent(), server.getPlayerList().getPlayers());
        event.setCanceled(true);
    }


}
