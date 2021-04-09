package com.nofrfa.enderpower.misc.jei.destructor;

import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.tile.machines.destructor.DestructorTE;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DestructorRecipeInit {
    private static final List<DestructorRecipeInit> recipes = new ArrayList<>();

    public static List<DestructorRecipeInit> getRecipes() { // Получатель всех рецептов.
        return recipes;
    }

    private final ItemStack input;
    private final List<ItemStack> op0;

    public DestructorRecipeInit(ItemStack input, List<ItemStack> output1) {
        this.input = input;
        this.op0 = output1;
    }

    public ItemStack getInput() { // Получатель входного предмета рецепта.
        return input;
    }

    public List<ItemStack> getOutput() { // Получатель выходного предмета рецепта.
        return op0;
    }

    public static DestructorRecipeInit addRecipe(ItemStack input, List<ItemStack> output1) {
        DestructorRecipeInit recipe = new DestructorRecipeInit(input, output1);
        if (recipes.contains(recipe))
            return null;
        recipes.add(recipe);
        return recipe;
    }

    public static DestructorRecipeInit getRecipe(ItemStack is) {
        if (is == null || is.isEmpty())
            return null;
        for (DestructorRecipeInit recipe : recipes)
            if (recipe.matchesInput(is))
                return recipe;
        return null;
    }

    public boolean matchesInput(ItemStack is) {
        return is.getItem() == input.getItem();
    }

    public static void initRecipes() {
        for(int i = 0; i < DestructorTE.inputItem.length; i++) {
            List<ItemStack> list = new ArrayList<>();
            for(int i1 = 0; i1 < DestructorTE.outputItem[i].length; i1++) {
                list.add(is(DestructorTE.outputItem[i][i1].getItem(), DestructorTE.outputItem[i][i1].getCount()));
            }
            addRecipe(DestructorTE.inputItem[i], list);
        }
    }

    private static ItemStack is(Item item, int amount) {
        return new ItemStack(item, amount);
    }
    private static ItemStack is(Item item) {
        return new ItemStack(item, 1);
    }

    private static ItemStack is(Block block, int amount) { return new ItemStack(block, amount); }
}
