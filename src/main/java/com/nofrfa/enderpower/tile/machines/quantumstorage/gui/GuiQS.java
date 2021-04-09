package com.nofrfa.enderpower.tile.machines.quantumstorage.gui;

import com.nofrfa.enderpower.misc.ModUtils;
import ic2.core.GuiIC2;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;

@SideOnly(Side.CLIENT)
public class GuiQS extends GuiIC2<ContainerQS> {
    public GuiQS(ContainerQS container) {
        super(container);
    }

    int scaleX = 0;

    @SideOnly(Side.CLIENT)
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);

        this.fontRenderer.drawString("Energy have " + this.container.base.getStored(), 50, 50, 15724524, true); // 17 043 521
        this.fontRenderer.drawString("Energy max " + this.container.base.getCapacity(), 50, 60, 15724524, true); // 4210752

        if(isMouseOver(mouseX, mouseY)) {
            this.drawTooltip(mouseX, mouseY, Collections.singletonList(ModUtils.getString(this.container.base.getStored()) + " / " + ModUtils.getString(this.container.base.getCapacity())));
        }

        scaleX = (int) this.container.base.getStored() / 17_043_521;
        this.mc.getTextureManager().bindTexture(getTexture());
        this.drawTexturedModalRect(25, 26, 0, 166, scaleX, 14);
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("enderpower", "textures/gui/gui_qs.png");
    }

    private final int x1 = this.guiLeft+25;
    private final int y1 = this.guiTop+26;

    private boolean isMouseOver(int mouseX, int mouseY){
        return mouseX >= this.x1 && mouseY >= this.y1 && mouseX < this.x1+126 && mouseY < this.y1+15;
    }
}
