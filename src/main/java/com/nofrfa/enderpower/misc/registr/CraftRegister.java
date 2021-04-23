package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.EnderPower;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftRegister {
    private static final ItemStack
            ultmachine = new ItemStack(BlocksRegister.BLOCK_UltimateMachineCasing),
            ult_circuit = new ItemStack(ItemsRegistry.ITEM_Ultimate_Circuit),
            adv_circuit = IC2Items.getItem("crafting", "advanced_circuit"),
            iodine = IC2Items.getItem("misc_resource", "iodine"),
            compressed_carbon_plate = new ItemStack(ItemsRegistry.ITEM_Compressed_Carbon_Plate),
            adv_casing_machine = IC2Items.getItem("resource", "advanced_machine"),
            power_convertion = new ItemStack(ItemsRegistry.MODULE_power_convertion),
            neifrit_coil = new ItemStack(ItemsRegistry.ITEM_neifrit_coil),
            neifrit = new ItemStack(ItemsRegistry.INGOT_nefrit),
            spadiy = new ItemStack(ItemsRegistry.INGOT_spadiy),
            upgrade = IC2Items.getItem("upgrade", "transformer"),
            lapotron_crystal = IC2Items.getItem("lapotron_crystal"),
            neifritpowder = new ItemStack(ItemsRegistry.DUST_neifrit, 4),
            dust_lapis = IC2Items.getItem("dust","lapis"),
            lithiumdust = IC2Items.getItem("dust","lithium"),
            energydust = IC2Items.getItem("dust","energium"),
            golddust = IC2Items.getItem("dust", "gold"),
            emptybich = new ItemStack(ItemsRegistry.ITEM_empshel),
            ukrglass = IC2Items.getItem("glass","reinforced"),
            heatingrod = new ItemStack(ItemsRegistry.ITEM_hetrod),
            ukrkamen = IC2Items.getItem("resource","reinforced_stone"),
            inhibiton_core = new ItemStack(ItemsRegistry.ITEM_The_inhibition), //Made in Mathew
            spadyplate = new ItemStack(ItemsRegistry.PLATE_spadiy),
            spadiydust = new ItemStack(ItemsRegistry.DUST_spadiy, 3),
            inhibition_core = new ItemStack(ItemsRegistry.ITEM_The_inhibition),
            neifrit_compres = new ItemStack(ItemsRegistry.PLATE_neifrit_compres),
            end_crystal = new ItemStack(Items.END_CRYSTAL),
            projectile = new ItemStack(ItemsRegistry.ITEM_deterrent),
            gold_ingot = new ItemStack(Items.GOLD_INGOT),
            gas_converter = EnderPower.machines.getItemStack(AllMachinesTE.gas_converter),
            destructor = EnderPower.machines.getItemStack(AllMachinesTE.destructor),
            irid_pate = IC2Items.getItem("crafting", "iridium"),
            erbi_ingot = new ItemStack(ItemsRegistry.INGOT_erbi),
            sp_3 = new ItemStack(ItemsRegistry.GENERATOR_sp_3),
            amulet_erbi = new ItemStack(ItemsRegistry.ITEM_erbi_amulet),

            //Теплообменники
            HEATSINK_1 = new ItemStack(ItemsRegistry.COMPONENT_1),
            HEATSINK_2 = new ItemStack(ItemsRegistry.COMPONENT_2),
            HEATSINK_3 = new ItemStack(ItemsRegistry.COMPONENT_3),
            HEATSINK_4 = new ItemStack(ItemsRegistry.COMPONENT_4),
            HEATSINK_5 = new ItemStack(ItemsRegistry.COMPONENT_5),
            HEATSINK_6 = new ItemStack(ItemsRegistry.COMPONENT_6),
            HEATSINK_7 = new ItemStack(ItemsRegistry.COMPONENT_7),

            HEATSINK_gold = gold_ingot,
            HEATSINK_coolant = IC2Items.getItem("fluid_cell", "ic2coolant"),
            HEATSINK_neifrit = neifrit,
            HEATSINK_neifrit_plate = new ItemStack(ItemsRegistry.PLATE_neifrit),
            HEATSINK_neifrit_plate_compress = neifrit_compres,
            HEATSINK_spadiy = spadiy,
            HEATSINK_spadiy_plate_compress = new ItemStack(ItemsRegistry.PLATE_spadiy_compres),
            HEATSINK_ult_cirtcuit = ult_circuit,
            HEATSINK_neifrit_casing = new ItemStack(ItemsRegistry.CASING_neifrit),

            HEATSINK_reactor_exchanger = IC2Items.getItem("reactor_heat_exchanger"),
            HEATSINK_reactor_heat_vent = IC2Items.getItem("reactor_heat_vent"),
            HEATSINK_component_exchanger = IC2Items.getItem("component_heat_exchanger"),
            HEATSINK_overclocked_heat_vent = IC2Items.getItem("overclocked_heat_vent"),
            HEATSINK_iridium_plate = IC2Items.getItem("crafting", "iridium"),
            HEATSINK_advanced_heat_exchanger = IC2Items.getItem("advanced_heat_exchanger"),
            HEATSINK_advanced_hear_vent = IC2Items.getItem("advanced_heat_vent"),
            //Улучшения
            FastQ = new ItemStack(ItemsRegistry.UPGRADE_speed),
            Iridium_Reflector = IC2Items.getItem("iridium_reflector"),
            diamond = new ItemStack(Items.DIAMOND),
            upgrade_neifrit = neifrit,
            lithium_dust = IC2Items.getItem("dust","lithium"),
            upgrade_overlocker = IC2Items.getItem("upgrade","overclocker"),
            Vy_upgrade = new ItemStack(ItemsRegistry.UPGRADE_Volecy),
            Energy_storage = IC2Items.getItem("upgrade","energy_storage"),
            steel_dust = new ItemStack(ItemsRegistry.DUST_steel),
            diamond_dust = IC2Items.getItem("dust", "diamond"),
            electrical_motor = IC2Items.getItem("crafting","electric_motor");

    public static void addCraftingRecipes() {
        addShapedRecipes((ult_circuit),
                "BCB",
                "CAC",
                "BCB",
                'A', adv_circuit, 'B', neifrit, 'C', iodine);

        addShapedRecipes((ultmachine),
                "ACA",
                "ABA",
                "ACA",
                'A', compressed_carbon_plate, 'B', adv_casing_machine, 'C', ult_circuit);

        addShapedRecipes((power_convertion),
                "ABA",
                "CDC",
                "ABA",
                'A', neifrit_coil, 'B', upgrade, 'C', lapotron_crystal, 'D', adv_circuit);

        addShapedRecipes((neifritpowder),
                "AB ",
                "CD ",
                "   ",
                'A', dust_lapis, 'B', lithiumdust, 'C', energydust, 'D', golddust);

        addShapedRecipes((emptybich),
                "ABA",
                "A A",
                "ABA",
                'A', ukrglass, 'B', neifrit);

        addShapedRecipes((heatingrod),
                "ADA",
                "BCB",
                "ADA",
                'A', ukrkamen, 'B', ukrglass, 'D', neifrit_coil, 'C', emptybich);

        addShapedRecipes((neifrit_coil),
                "ABA",
                "ABA",
                "ABA",
                'A', neifrit, 'B', spadiy);

        addShapedRecipes((projectile),
                "DBD",
                "ACA",
                "AAA",
                'A', neifrit_compres, 'B', inhibiton_core, 'C', end_crystal, 'D', spadyplate);

        addShapedRecipes((inhibition_core),
                "CBC",
                "CBC",
                "AAA",
                'A', neifrit_compres, 'B', gold_ingot, 'C', spadyplate);

        addShapedRecipes((HEATSINK_1),
                "ABA",
                "DCD",
                "DED",
                'A', HEATSINK_neifrit, 'B', HEATSINK_gold, 'C', "ingotSilver", 'D', HEATSINK_neifrit_casing, 'E', HEATSINK_coolant);

        addShapedRecipes((HEATSINK_2),
                "ADA",
                "DBD",
                "CDC",
                'A', "ingotCopper", 'B', HEATSINK_1, 'C', HEATSINK_gold, 'D', HEATSINK_neifrit_casing);

        addShapedRecipes((HEATSINK_3),
                "DBD",
                "EAE",
                "BCB",
                'A', HEATSINK_2, 'B', "ingotSilver", 'C', HEATSINK_gold, 'D', HEATSINK_spadiy, 'E', HEATSINK_neifrit_plate);

        addShapedRecipes((HEATSINK_4),
                "DBD",
                "BAB",
                "DBD",
                'A', HEATSINK_3, 'B', HEATSINK_neifrit_plate_compress, 'D', HEATSINK_spadiy_plate_compress);

        addShapedRecipes((HEATSINK_5),
                "DCB",
                "CAC",
                "BCD",
                'A', HEATSINK_4, 'B', HEATSINK_ult_cirtcuit, 'C', HEATSINK_reactor_exchanger, 'D', HEATSINK_reactor_heat_vent);

        addShapedRecipes((HEATSINK_6),
                "CBD",
                "BAB",
                "DBC",
                'A', HEATSINK_5, 'B', HEATSINK_component_exchanger, 'C', HEATSINK_overclocked_heat_vent, 'D', HEATSINK_iridium_plate);

        addShapedRecipes((HEATSINK_7),
                "CDB",
                "DAD",
                "BDC",
                'A', HEATSINK_6, 'B', Iridium_Reflector, 'D', HEATSINK_advanced_heat_exchanger, 'C', HEATSINK_advanced_hear_vent);

        addShapedRecipes((FastQ),
                "BCB",
                "BEB",
                "ABA",
                'A', Iridium_Reflector, 'B', upgrade_neifrit, 'C', lithium_dust, 'E', upgrade_overlocker);

        addShapedRecipes((Vy_upgrade),
                "DAD",
                "DCD",
                "BDB",
                'A', steel_dust, 'B', Energy_storage, 'D', diamond, 'C', electrical_motor);

        addShapedRecipes((spadiydust),
                "DDD",
                "AAA",
                "BBB",
                'A', "dustLead", 'B', steel_dust, 'D', diamond_dust);

        addShapedRecipes((gas_converter),
                "ABA",
                "CDC",
                "EBE",
                'A', irid_pate, 'B', electrical_motor, 'C', ult_circuit, 'D', ultmachine, 'E', compressed_carbon_plate);

        addShapedRecipes((destructor),
                "AEA",
                "CDC",
                "ABA",
                'A', irid_pate, 'B', heatingrod, 'C', ult_circuit, 'D', ultmachine, 'E', power_convertion);

        addShapelessRecipe(erbi_ingot, sp_3, sp_3);

        addShapedRecipes((amulet_erbi),
                "ABA",
                "BCB",
                "ABA",
                'A', erbi_ingot, 'B', irid_pate, 'C', ult_circuit);
    }

    private static void addShapedRecipes (ItemStack output, Object...input){
        ic2.api.recipe.Recipes.advRecipes.addRecipe(output, input);
    }

    private static void addShapelessRecipe (ItemStack output, Object...input) {
        Recipes.advRecipes.addShapelessRecipe(output, input);
    }
}