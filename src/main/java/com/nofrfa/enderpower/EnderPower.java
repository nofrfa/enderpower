package com.nofrfa.enderpower;

import com.nofrfa.enderpower.misc.registr.AllMachinesTE;
import com.nofrfa.enderpower.proxy.CommonProxy;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.BlockTileEntity;
import ic2.core.block.TeBlockRegistry;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = EnderPower.MODID,
        name = EnderPower.NAME,
        version = EnderPower.VERSION,
        acceptedMinecraftVersions = EnderPower.STABLEVERSION,
        dependencies = "required-after:ic2@[2.8.192-ex112, 2.8.221-ex112];"
)
public class EnderPower {
    public static final String MODID = "enderpower";
    public static final String NAME = "Ender Power";
    public static final String VERSION = "1.0.0";
    public static final String STABLEVERSION = "1.12.2";

    public static Logger log;

    @SidedProxy(
            clientSide = "com.nofrfa.enderpower.proxy.ClientProxy",
            serverSide = "com.nofrfa.enderpower.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.Instance
    public static EnderPower instance;

    public static BlockTileEntity machines;

    @EventHandler
    public void start(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void register(TeBlockFinalCallEvent event) {
        TeBlockRegistry.addAll(AllMachinesTE.class, AllMachinesTE.LOCATIONRES);
        TeBlockRegistry.setDefaultMaterial(AllMachinesTE.LOCATIONRES, Material.ROCK);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        machines = TeBlockRegistry.get(AllMachinesTE.LOCATIONRES);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) { proxy.postInit(event); }
}