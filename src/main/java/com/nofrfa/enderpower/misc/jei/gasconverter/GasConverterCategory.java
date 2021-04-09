package com.nofrfa.enderpower.misc.jei.gasconverter;

import com.nofrfa.enderpower.EnderPower;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GasConverterCategory extends Gui implements IRecipeCategory<GasConverterWrapper> {
    public static final String UID = EnderPower.MODID + ":gas_converter"; // Сам UID рецепт.

    private final IDrawableStatic bg; // "Background"
    private int progress = 0;

    public GasConverterCategory(IGuiHelper h) {
        bg = h.createDrawable(new ResourceLocation(EnderPower.MODID, "textures/gui/jei.gas_converter.png"), 0, 0, 166, 128);
    }

    @Override
    public String getUid() { // UID рецепта.
        return UID;
    }

    @Override
    public String getTitle() { // Название вкладки в MC, можно использовать I18n переводчик.
        return I18n.format("enderpower.machines.gas_converter");
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
        int xScale = progress / 25;
        if (xScale > 20) {
            progress = 0;
        }
        mc.getTextureManager().bindTexture(getTexture());
        drawTexturedModalRect(55, 29, 196, 0, xScale, 3);

        mc.fontRenderer.drawString(I18n.format("jei.desc.extra"), 5, 80, 0xffffffff, true);
        mc.fontRenderer.drawString(I18n.format("jei.desc.extra2"), 5, 87, 0xffffffff, true);
        mc.fontRenderer.drawString(I18n.format("jei.desc.extra3"), 5, 94, 0xffffffff, true);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, GasConverterWrapper recipes, IIngredients ingredients) {

        IGuiItemStackGroup isg = layout.getItemStacks(); // Группа ItemStack, которая нужна для рендера.
        IGuiFluidStackGroup fff = layout.getFluidStacks();
        // Все координаты идут относительно самого рецепта. Все width и height рассчитывать не нужно.
        isg.init(0, true, 33, 28); // Инициализируем слот 0. true/false - это обозначение того, является ли слот *ВХОДНЫМ*, true - да, false - нет. Остальные 2 числа - координаты. X/Y.
        isg.set(0, recipes.getInput()); // Добавляем в слот 0 входной предмет.

        isg.init(1, false, 51, 57); // Инициализируем слот 1.  true/false - это обозначение того, является ли слот *ВХОДНЫМ*, true - да, false - нет. Остальные 2 числа - координаты. X/Y.
        isg.set(1, recipes.getOutput()); // Добавляем в слот 1 выходной предмет.

        fff.init(2, false, 90, 13, 12, 47, 10000, true, null);
        fff.set(2, recipes.getOutputFluid());
    }

    protected ResourceLocation getTexture() {
        return new ResourceLocation(EnderPower.MODID, "textures/gui/gui_gas.png");
    }
}
