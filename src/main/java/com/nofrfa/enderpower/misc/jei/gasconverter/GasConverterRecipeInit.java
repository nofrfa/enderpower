package com.nofrfa.enderpower.misc.jei.gasconverter;

import com.nofrfa.enderpower.misc.registr.FluidsRegister;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class GasConverterRecipeInit {
    private static List<GasConverterRecipeInit> recipes = new ArrayList<GasConverterRecipeInit>();

    public static List<GasConverterRecipeInit> getRecipes() { // Получатель всех рецептов.
        return recipes;
    }

    private final ItemStack input, output;
    private FluidStack outputFluid;

    public GasConverterRecipeInit(ItemStack input, ItemStack output, FluidStack fluid) {
        this.input = input;
        this.output = output;
        this.outputFluid = fluid;
    }

    public ItemStack getInput() { // Получатель входного предмета рецепта.
        return input;
    }

    public ItemStack getOutput() { // Получатель выходного предмета рецепта.
        return output.copy();
    }

    public FluidStack getOutputFluid() {
        return outputFluid;
    }

    public static GasConverterRecipeInit addRecipe(ItemStack input, ItemStack output, FluidStack fluidOutput) {
        GasConverterRecipeInit recipe = new GasConverterRecipeInit(input, output, fluidOutput);
        if (recipes.contains(recipe))
            return null;
        recipes.add(recipe);
        return recipe;
    }

    public static GasConverterRecipeInit getRecipe(ItemStack is) {
        if (is == null || is.isEmpty())
            return null;
        for (GasConverterRecipeInit recipe : recipes)
            if (recipe.matchesInput(is))
                return recipe;
        return null;
    }

    public boolean matchesInput(ItemStack is) {
        return is.getItem() == input.getItem();
    }

    public static void initRecipes() {
        NBTTagCompound nbttt = new NBTTagCompound();
        nbttt.setString("inside", "shulker_projectile");

        ItemStack ItemInput = is(ItemsRegistry.ITEM_deterrent_filled);
        ItemInput.setTagCompound(nbttt);

        addRecipe(ItemInput, is(ItemsRegistry.ITEM_deterrent), new FluidStack(FluidsRegister.GAS_ERBI, 250));
    }

    private static ItemStack is(Item item) { // Побочный метод.
        return new ItemStack(item);
    }

    private static ItemStack is(Block block) { // Побочный метод.
        return new ItemStack(block);
    }
}
