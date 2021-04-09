package com.nofrfa.enderpower.misc.jei.gasconverter;

import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterRecipeInit;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GasConverterWrapper implements IRecipeWrapper {
    private ItemStack input; // Объявление входного предмета для рецепта.
    private ItemStack output; // Объявление выходного листа для рецепта.
    private FluidStack outputFluid;

    public GasConverterWrapper(GasConverterRecipeInit recipe) { // Сам рецепт. Так же можно передавать сюда свои рецепты и разбирать его на входные/выходные предметы.
        input = recipe.getInput(); // Собственно сам входной предмет.
        output = recipe.getOutput(); // Собственно сам выходной предмет.
        outputFluid = recipe.getOutputFluid();
    }

    @Override
    public void getIngredients(IIngredients ingredients) { // Ингридиенты рецепта.
        ingredients.setInput(ItemStack.class, input); // Входные ингридиенты.
        ingredients.setOutput(ItemStack.class, output); // Выходные ингридиенты.
        ingredients.setOutput(FluidStack.class, outputFluid);
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public FluidStack getOutputFluid() {
        return outputFluid;
    }
}
