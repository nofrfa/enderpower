package com.nofrfa.enderpower.misc.jei.destructor;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.misc.jei.gasconverter.GasConverterWrapper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DestructorCategory extends Gui implements IRecipeCategory<DestructorWrapper> {
    public static final String UID = EnderPower.MODID + ":destr";

    private final IDrawableStatic bg;
    private int progress = 0;

    public DestructorCategory(IGuiHelper h) {
        bg = h.createDrawable(new ResourceLocation(EnderPower.MODID, "textures/gui/jei.destr.png"), 0, 0, 165, 78);
    }

    @Override
    public String getUid() { // UID рецепта.
        return UID;
    }

    @Override
    public String getTitle() { // Название вкладки в MC, можно использовать I18n переводчик.
        return I18n.format("enderpower.machines.destructor");
    }

    @Override
    public String getModName() { // Название мода.
        return EnderPower.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return bg;
    }

    @Override
    public void drawExtras(Minecraft mc) {
        progress++;
        int xScale = progress / 15;
        if (xScale > 67) {
            progress = 0;
        }
        mc.getTextureManager().bindTexture(getTexture());
        drawTexturedModalRect(40, 16, 176, 0, xScale, 43);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, DestructorWrapper recipes, IIngredients ingredients) {
        IGuiItemStackGroup isg = layout.getItemStacks();

        isg.init(0, true, 19, 29);
        isg.set(0, recipes.getInput());

        isg.init(1, false, 109, 20);
        isg.set(1, recipes.getOutput().get(0));

        switch (recipes.getOutput().size()) {
            case(4):
                isg.init(4, false, 127, 38);
                isg.set(4, recipes.getOutput().get(3));
            case(3):
                isg.init(3, false, 109, 38);
                isg.set(3, recipes.getOutput().get(2));
            case(2):
                isg.init(2, false, 127, 20);
                isg.set(2, recipes.getOutput().get(1));
        }
    }

    protected ResourceLocation getTexture() {
        return new ResourceLocation(EnderPower.MODID, "textures/gui/gui_destructor.png");
    }
}
