package com.nofrfa.enderpower.tile.machines.gasconverter.gui;

import ic2.core.GuiIC2;
import ic2.core.gui.EnergyGauge;
import ic2.core.gui.TankGauge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGas extends GuiIC2<ContainerGas> {
    public GuiGas(ContainerGas container) {
        super(container);
        addElement(EnergyGauge.asBolt(this, 27, 36, container.base));
        addElement(TankGauge.createNormal(this, 90, 15, container.base.fluidTank));
    }

    @SideOnly(Side.CLIENT)
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);

        int xScale = this.container.base.getProgress() / 5;

        this.mc.getTextureManager().bindTexture(getTexture());
        this.drawTexturedModalRect(59, 35, 196, 0, xScale, 3);

        this.fontRenderer.drawString(this.container.base.getProgress() + "%", 41, 25, 4210752);
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("enderpower", "textures/gui/gui_gas.png");
    }

}
