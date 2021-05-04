package com.nofrfa.enderpower.tile.machines.destructor.gui;

import ic2.core.GuiIC2;
import ic2.core.gui.EnergyGauge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDestr extends GuiIC2<ContainerDestr> {
    public GuiDestr(ContainerDestr container) {
        super(container);
        addElement(EnergyGauge.asBolt(this, 16, 37, container.base));
    }

    @SideOnly(Side.CLIENT)
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);

        int xScale = (int) (this.container.base.getProgress() / 1.55);

        this.mc.getTextureManager().bindTexture(getTexture());
        this.drawTexturedModalRect(46, 21, 176, 0, xScale, 43);
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("enderpower", "textures/gui/gui_destructor.png");
    }
}
