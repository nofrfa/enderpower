package com.nofrfa.enderpower.misc.jei;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.misc.jei.destructor.DestructorCategory;
import com.nofrfa.enderpower.misc.jei.destructor.DestructorRecipeInit;
import com.nofrfa.enderpower.misc.jei.destructor.DestructorWrapper;
import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterCategory;
import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterRecipeInit;
import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterWrapper;
import com.nofrfa.enderpower.misc.registr.AllMachinesTE;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.tile.machines.destructor.gui.GuiDestr;
import com.nofrfa.enderpower.tile.machines.gasconverter.gui.GuiGas;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIMain implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GasConverterCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new DestructorCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipeClickArea(GuiDestr.class, 46, 21, 67, 43, DestructorCategory.UID);
        registry.addRecipes(DestructorRecipeInit.getRecipes(), DestructorCategory.UID);
        registry.handleRecipes(DestructorRecipeInit.class, DestructorWrapper::new, DestructorCategory.UID);
        registry.addRecipeCatalyst(EnderPower.machines.getItemStack(AllMachinesTE.destructor), DestructorCategory.UID);

        registry.addRecipeClickArea(GuiGas.class, 58, 34, 21, 5, GasConverterCategory.UID);
        registry.addRecipes(GasConverterRecipeInit.getRecipes(), GasConverterCategory.UID);
        registry.handleRecipes(GasConverterRecipeInit.class, GasConverterWrapper::new, GasConverterCategory.UID);
        registry.addRecipeCatalyst(EnderPower.machines.getItemStack(AllMachinesTE.gas_converter), GasConverterCategory.UID);


        registry.addDescription(new ItemStack(ItemsRegistry.ITEM_deterrent), I18n.format("jei.desc.projectile_holder"));
        registry.addDescription(new ItemStack(ItemsRegistry.DUST_spadiy), I18n.format("jei.desc.dust_spidiy"));
    }
}
