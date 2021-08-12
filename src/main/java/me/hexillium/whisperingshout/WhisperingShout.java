package me.hexillium.whisperingshout;

import me.hexillium.whisperingshout.commands.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = WhisperingShout.MOD_ID, name = WhisperingShout.NAME, version = WhisperingShout.VERSION,
        serverSideOnly = true, acceptableRemoteVersions = "*", acceptedMinecraftVersions = "[1.12.2]")
public class WhisperingShout{

    public static final String MOD_ID = "whisperingshout";
    public static final String NAME = "Whispering Shout";
    public static final String VERSION = "1.0";
    public static final String NBT_PATH = "whisperingshout.chat.type";

    private static Logger logger;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.init(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new ChatHandler());
    }

    @Mod.EventHandler
    public void start(FMLServerStartingEvent event){
        event.registerServerCommand(new Whisper());
        event.registerServerCommand(new Talk());
        event.registerServerCommand(new Shout());
        event.registerServerCommand(new OutOfCharacter());
    }

    public static ChatType registerDefault(EntityPlayerMP player, ChatType type){
        NBTTagCompound tag = player.getEntityData();
        ChatType current = ChatType.fromInt(tag.getInteger(NBT_PATH));
        if (type == current){
            type = ChatType.UNSPECIFIED;
        }
        tag.setInteger(NBT_PATH, type.getType());
        return type;
    }

    public static ChatType getChatPreference(EntityPlayerMP player){
        return ChatType.fromInt(player.getEntityData().getInteger(NBT_PATH));
    }

}
