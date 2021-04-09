package com.nofrfa.enderpower.proxy;

import com.nofrfa.enderpower.misc.jei.destructor.DestructorRecipeInit;
import com.nofrfa.enderpower.misc.registr.EnderPowerCommand;
import com.nofrfa.enderpower.misc.events.EventsHandler;
import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterRecipeInit;
import com.nofrfa.enderpower.misc.registr.AllMachinesTE;
import com.nofrfa.enderpower.misc.registr.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        FluidsRegister.register();
        BlocksRegister.register();
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
    }

    public void init(FMLInitializationEvent event) {
        AllMachinesTE.buildDummies();
        CraftMachinesRegister.addMachineRecipe();
        CraftRegister.addCraftingRecipes();
        GasConverterRecipeInit.initRecipes();
        DestructorRecipeInit.initRecipes();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new EnderPowerCommand());
    }

}