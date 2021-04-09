package com.nofrfa.enderpower.tile.machines.erbigen.gui;

import ic2.core.GuiIC2;
import ic2.core.IC2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiEG extends GuiIC2<ContainerEG> {
    public GuiEG(ContainerEG container) {
        super(container, 256, 186);
        //addElement(TankGauge.createPlain(this, 187, 10, 48, 87, container.base.fluidTank));
    }

    @SideOnly(Side.CLIENT)
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);

        this.mc.getTextureManager().bindTexture(getTexture());
        this.drawTexturedModalRect(187, 20, 0, 186, 48, 70);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) throws IOException {
        super.actionPerformed(guibutton);
        IC2.network.get(false).initiateClientTileEntityEvent(this.container.base, guibutton.id);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, (this.width - this.xSize) / 2 + 6 + 10, (this.height - this.ySize + 1) / 2 + 6, 20, 20, ""));
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("enderpower", "textures/gui/gui_eg.png");
    }
}
