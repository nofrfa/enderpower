package com.nofrfa.enderpower.misc.jei.destructor;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.List;

public class DestructorWrapper implements IRecipeWrapper {
    private ItemStack input;
    private List<ItemStack> output0;

    public DestructorWrapper(DestructorRecipeInit recipe) {
        input = recipe.getInput();
        output0 = recipe.getOutput();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output0);
    }

    public ItemStack getInput() {
        return input;
    }

    public List<ItemStack> getOutput() {
        return output0;
    }
}
