package com.nofrfa.enderpower.misc.registr;

import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CraftMachinesRegister {
    private static final ItemStack
            carbon_plate = IC2Items.getItem("crafting", "carbon_plate"),
            steel_powder = new ItemStack(ItemsRegistry.DUST_steel),
            spadiy = new ItemStack(ItemsRegistry.INGOT_spadiy),
            iron_dust = IC2Items.getItem("dust", "iron"),
            spadiy_plate = new ItemStack(ItemsRegistry.PLATE_spadiy),
            spadiy_plate_compress = new ItemStack(ItemsRegistry.PLATE_spadiy_compres),
            neifrit = new ItemStack(ItemsRegistry.INGOT_nefrit),
            neifrit_plate = new ItemStack(ItemsRegistry.PLATE_neifrit),
            neifrit_plate_compres = new ItemStack(ItemsRegistry.PLATE_neifrit_compres),
            neifrit_casing = new ItemStack(ItemsRegistry.CASING_neifrit, 2),
            neifritpowder = new ItemStack(ItemsRegistry.DUST_neifrit),
            sp_0 = new ItemStack(ItemsRegistry.GENERATOR_sp_0),
            sp_1 = new ItemStack(ItemsRegistry.GENERATOR_sp_1),
            sp_2 = new ItemStack(ItemsRegistry.GENERATOR_sp_2),
            sp_3 = new ItemStack(ItemsRegistry.GENERATOR_sp_3);

    public static void addMachineRecipe() {
        IRecipeInputFactory input = Recipes.inputFactory;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("minHeat", 3000);

        iron_dust.setCount(3);

        addCompressorRecipe(input.forStack(carbon_plate, 9), new ItemStack(ItemsRegistry.ITEM_Compressed_Carbon_Plate));
        addCompressorRecipe(input.forStack(neifrit_plate, 9), neifrit_plate_compres);
        addCompressorRecipe(input.forStack(spadiy_plate, 9), spadiy_plate_compress);
        addCompressorRecipe(input.forStack(neifritpowder, 6), neifrit);
        addCompressorRecipe(input.forStack(sp_0, 2), sp_1);

        addMedCentrifugeRecipe(input.forOreDict("ingotSteel", 4), steel_powder, iron_dust, nbt);
        addOneCentrifugeRecipe(input.forStack(sp_1, 4), sp_2, nbt);

        addRollingRecipe(input.forStack(spadiy), spadiy_plate);
        addRollingRecipe(input.forStack(neifrit), neifrit_plate);
        addRollingRecipe(input.forStack(neifrit_plate), neifrit_casing);

        //addExtrudingRecipe(input.forStack(new ItemStack(ItemsRegistry.INGOT_spadiy), 8), new ItemStack(ItemsRegistry.ITEM_deterrent));
    }

    private static void addCompressorRecipe(IRecipeInput input, ItemStack output) {
        Recipes.compressor.addRecipe(input, (NBTTagCompound)null, false, output);
    }

    private static void addRollingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerRolling.addRecipe(input, (NBTTagCompound)null, false, output);
    }

    private static void addOneCentrifugeRecipe(IRecipeInput input, ItemStack output, NBTTagCompound nbt) {
        Recipes.centrifuge.addRecipe(input, (NBTTagCompound)nbt, false, output);
    }

    private static void addMedCentrifugeRecipe(IRecipeInput input, ItemStack output, ItemStack output2, NBTTagCompound nbt) {
        Recipes.centrifuge.addRecipe(input, (NBTTagCompound)nbt, false, output, output2);
    }
}
