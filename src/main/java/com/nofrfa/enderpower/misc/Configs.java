package com.nofrfa.enderpower.misc;

import com.nofrfa.enderpower.EnderPower;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = EnderPower.MODID)
@Mod.EventBusSubscriber(modid = EnderPower.MODID)
public class Configs {
    @Config.Ignore
    private static final String config = EnderPower.MODID + ".config.";

    @Config.LangKey(config + "general_settings")
    @Config.Comment("Here you can change general settings")
    public static GeneralSettings GeneralSettings = new GeneralSettings();
    public static class GeneralSettings {
        @Config.LangKey(config + "general_settings.item")
        @Config.Comment("Here you can change item specifications")
        public Item Item = new Item();
        public static class Item {
            @Config.LangKey(config + "general_settings.item.amulet_erbi")
            @Config.Comment("Responsible for the durability of the amulet erbi")
            @Config.RangeInt(min = 1, max = 128)
            @Config.RequiresMcRestart
            public int amuletErbi_durability = 24;

            @Config.LangKey(config + "general_settings.item.heat_sink1")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink1_durability = 2400;

            @Config.LangKey(config + "general_settings.item.heat_sink2")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink2_durability = 4800;

            @Config.LangKey(config + "general_settings.item.heat_sink3")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink3_durability = 6600;

            @Config.LangKey(config + "general_settings.item.heat_sink4")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink4_durability = 7800;

            @Config.LangKey(config + "general_settings.item.heat_sink5")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink5_durability = 9600;

            @Config.LangKey(config + "general_settings.item.heat_sink6")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink6_durability = 10800;

            @Config.LangKey(config + "general_settings.item.heat_sink7")
            @Config.Comment("Responsible for the durability of the heat sink")
            @Config.RangeInt(min = 0, max = 1000000)
            @Config.RequiresMcRestart
            public int heatSink7_durability = 12000;
        }

        @Config.LangKey(config + "general_settings.upgrades")
        @Config.Comment("Here you can change upgrades specifications")
        public Upgrades Upgrades = new Upgrades();
        public static class Upgrades {
            @Config.LangKey(config + "general_settings.upgrades.fastq")
            public FastQ FastQ = new FastQ();
            public static class FastQ {
                @Config.LangKey(config + "general_settings.upgrades.fastq_boost_speed")
                @Config.Comment("Increases the speed of the mechanism")
                @Config.RangeInt(min = 1, max = 20)
                public int upgrades_fastq_boostSpeed = 4;

                @Config.LangKey(config + "general_settings.upgrades.fastq_increase_energy_consume")
                @Config.Comment("Increases the consume energy of the mechanism")
                @Config.RangeInt(min = 64, max = 512)
                public int upgrades_fastq_increaseEnergyConsume = 128;
            }

            @Config.LangKey(config + "general_settings.upgrades.volecy")
            public Volecy Volecy = new Volecy();
            public static class Volecy {
                @Config.LangKey(config + "general_settings.upgrades.volecy_boost_output_mb")
                @Config.Comment("Increases the output mB (only Gas Erbi) of the mechanism")
                @Config.RangeInt(min = 20, max = 200)
                public int upgrades_volecy_mbBoost = 50;

                @Config.LangKey(config + "general_settings.upgrades.volecy_increase_energy_consume")
                @Config.Comment("Increases the consume energy of the mechanism")
                @Config.RangeInt(min = 64, max = 1024)
                public int upgrades_volecy_increaseEnergyConsume = 256;
            }
        }

        @Config.LangKey(config + "general_settings.mechanisms")
        @Config.Comment("Here you can change mechanisms specifications")
        public Mechanisms Mechanisms = new Mechanisms();
        public static class Mechanisms {
            @Config.LangKey(config + "general_settings.mechanisms.gas_extractor")
            public GasExtractor GasExtractor = new GasExtractor();
            public static class GasExtractor {
                @Config.LangKey(config + "general_settings.mechanisms.gas_extractor.output_mb")
                @Config.Comment("How many mB will you get when processing in a gas extractor without upgrades")
                @Config.RangeInt(min = 200, max = 1200)
                @Config.RequiresMcRestart
                public int defaultOutputMb = 250;

                @Config.LangKey(config + "general_settings.mechanisms.gas_extractor.energy_consume")
                @Config.Comment("How much energy(eu/t) will the mechanism consume without upgrades")
                @Config.RangeInt(min = 64, max = 1024)
                @Config.RequiresMcRestart
                public int defaultEnergyConsume = 256;
            }

            @Config.LangKey(config + "general_settings.mechanisms.destructor")
            public Destructor Destructor = new Destructor();
            public static class Destructor {
                @Config.LangKey(config + "general_settings.mechanisms.destructor.energy_consume")
                @Config.Comment("How much energy(eu/t) will the mechanism consume without upgrades")
                @Config.RangeInt(min = 64, max = 2048)
                @Config.RequiresMcRestart
                public int defaultEnergyConsume = 768;
            }

            @Config.LangKey(config + "general_settings.mechanisms.erbi_generator")
            public ErbiGenerator Erbi_Generator = new ErbiGenerator();
            public static class ErbiGenerator {
                @Config.LangKey(config + "general_settings.mechanisms.erbi_generator.max_energy_capacity")
                @Config.Comment("Internal energy buffer (How much can be stored)\nP.s - add D at the end (numberD)")
                @Config.RangeDouble(min = 120_000_000, max = 9_223_372_036_854_775_807D)
                @Config.RequiresMcRestart
                public double defaultEnergyCapacity = 10_000_000_000D;

                @Config.LangKey(config + "general_settings.mechanisms.erbi_generator.max_temperature")
                @Config.Comment("Maximum temperature in mechanism")
                @Config.RangeInt(min = 5000, max = 12000)
                @Config.RequiresMcRestart
                public int defaultMaxTemperature = 6000;

                @Config.LangKey(config + "general_settings.mechanisms.erbi_generator.production")
                @Config.Comment(" /// ") // TODO: 09.04.2021
                @Config.RangeInt(min = 0)
                @Config.RequiresMcRestart
                public int defaultProduction = 400_000;
            }
        }

        @Config.LangKey(config + "general_settings.activate_high_energy_tier")
        @Config.Comment("If you turn it on, some mechanisms will have a high energy level (above 5)\nYou should turn off the restriction in the wires and the explosions in ic2\nP.s. enableEnetExplosions and enableEnetCableMeltdown")
        public boolean activateHighEnergyTier = false;

        @Config.LangKey(config + "general_settings.root_access")
        @Config.Comment("This variable is responsible for activating functions that for some reason were not removed, but were placed under the check of this variable, so if you are very interested in what might appear in the next version of the addon, feel free to activate :)")
        @Config.RequiresMcRestart
        public boolean root_access = false;
    }

    public static int getErbiGeneratorTier() {
        if(GeneralSettings.activateHighEnergyTier)
            return 15;
        else
            return 4;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(EnderPower.MODID)) {
            ConfigManager.sync(EnderPower.MODID, Config.Type.INSTANCE);
        }
    }
}
