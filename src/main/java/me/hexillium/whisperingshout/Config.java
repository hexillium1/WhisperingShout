package me.hexillium.whisperingshout;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber
public class Config {
    private static Configuration config;
    public static boolean chat_enabled;
    public static boolean chat_showDistances;
    public static boolean chat_squareRange;
    public static boolean chat_check_dimension;
    public static   float chat_range;

    public static boolean whisper_enabled;
    public static boolean whisper_showDistances;
    public static boolean whisper_squareRange;
    public static boolean whisper_check_dimension;
    public static   float whisper_range;

    public static boolean talk_enabled;
    public static boolean talk_showDistances;
    public static boolean talk_squareRange;
    public static boolean talk_check_dimension;
    public static   float talk_range;

    public static boolean shout_enabled;
    public static boolean shout_showDistances;
    public static boolean shout_squareRange;
    public static boolean shout_check_dimension;
    public static   float shout_range;

    public static boolean outofcharacter_enabled;


    public static void init(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), WhisperingShout.MOD_ID + ".cfg");
        config = new Configuration(configFile);
        config.load();
        setConfig();
    }

    private static void setConfig() {


        chat_enabled = config.getBoolean("enabled", "default_chat", true, "Whether or not this mod should modify the chat. Disabling this does not disable default Minecraft chat.");
        chat_squareRange = config.getBoolean("square_range", "default_chat", false, "Mode of checking range. Square compares the sum of XYZ, setting to false uses Euclidean distance.");
        chat_showDistances = config.getBoolean("show_distances", "default_chat", true, "Show from how far away (in blocks/metres) the message was sent.");
        chat_check_dimension = config.getBoolean("check_dimension", "default_chat", true, "Set to true to deny cross-dimension ranged chat. False will not factor dimension and only check coordinates.");
        chat_range = config.getFloat("range", "default_chat", 25.0f, 0f, 1_000_000f, "The range of standard chat messages.");

        whisper_enabled = config.getBoolean("enabled", "whisper_command", true, "Whether or not the whisper command should be enabled.");
        whisper_squareRange = config.getBoolean("square_range", "whisper_command", false, "Mode of checking range. Square compares the sum of XYZ, setting to false uses Euclidean distance.");
        whisper_showDistances = config.getBoolean("show_distances", "whisper_command", true, "Show from how far away (in blocks/metres) the message was sent.");
        whisper_check_dimension = config.getBoolean("check_dimension", "whisper_command", true, "Set to true to deny cross-dimension ranged chat. False will not factor dimension and only check coordinates.");
        whisper_range = config.getFloat("range", "whisper_command", 10.0f, 0f, 1_000_000f, "The range of standard chat messages.");

        talk_enabled = config.getBoolean("enabled", "talk_command", true, "Whether or not the talk command should be enabled.");
        talk_squareRange = config.getBoolean("square_range", "talk_command", false, "Mode of checking range. Square compares the sum of XYZ, setting to false uses Euclidean distance.");
        talk_showDistances = config.getBoolean("show_distances", "talk_command", true, "Show from how far away (in blocks/metres) the message was sent.");
        talk_check_dimension = config.getBoolean("check_dimension", "talk_command", true, "Set to true to deny cross-dimension ranged chat. False will not factor dimension and only check coordinates.");
        talk_range = config.getFloat("range", "talk_command", 25.0f, 0f, 1_000_000f, "The range of standard chat messages.");

        shout_enabled = config.getBoolean("enabled", "shout_command", true, "Whether or not the shout command should be enabled.");
        shout_squareRange = config.getBoolean("square_range", "shout_command", false, "Mode of checking range. Square compares the sum of XYZ, setting to false uses Euclidean distance.");
        shout_showDistances = config.getBoolean("show_distances", "shout_command", true, "Show from how far away (in blocks/metres) the message was sent.");
        shout_check_dimension = config.getBoolean("check_dimension", "shout_command", true, "Set to true to deny cross-dimension ranged chat. False will not factor dimension and only check coordinates.");
        shout_range = config.getFloat("range", "shout_command", 50.0f, 0f, 1_000_000f, "The range of standard chat messages.");

        outofcharacter_enabled = config.getBoolean("enabled", "outofcharacter_command", true, "Whether or not the out-of-character command should be enabled.");



        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(WhisperingShout.MOD_ID)) {
            setConfig();
        }
    }
}
