package com.nofrfa.enderpower.tile.machines.erbigen.gui;

import com.nofrfa.enderpower.misc.ModUtils;
import ic2.core.GuiIC2;
import ic2.core.IC2;
import ic2.core.gui.TankGauge;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiEG extends GuiIC2<ContainerEG> {
    public GuiEG(ContainerEG container) {
        super(container, 216, 256);
        addElement(TankGauge.createPlain(this, 117, 9, 93, 43, container.base.fluidTank));
    }

    @SideOnly(Side.CLIENT)
    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        if(isMouseOverPromt(mouseX, mouseY)) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation("enderpower", "textures/gui/gui_other.png"));
            List<String> prompt = new ArrayList<>();

            prompt.add("[1] - " + I18n.format("erbigenerator.gui.prompt_line1"));
            prompt.add("[2] - " + I18n.format("erbigenerator.gui.prompt_line2"));
            prompt.add("[3] - " + I18n.format("erbigenerator.gui.prompt_line3"));
            prompt.add("[4] - " + I18n.format("erbigenerator.gui.prompt_line4"));
            prompt.add("[5] - " + I18n.format("erbigenerator.gui.prompt_line5"));
            prompt.add("[6] - " + I18n.format("erbigenerator.gui.prompt_line6"));
            prompt.add("[7] - " + I18n.format("erbigenerator.gui.prompt_line7"));
            prompt.add("[8] - " + I18n.format("erbigenerator.gui.prompt_line8"));
            prompt.add("[9] - " + I18n.format("erbigenerator.gui.prompt_line9"));

            this.drawTooltip(mouseX, mouseY, prompt);
            this.drawTexturedModalRect(206,  146, 9, 42, 123, 146);

            this.mc.getTextureManager().bindTexture(new ResourceLocation("enderpower", "textures/gui/gui_eg_prompt.png"));
            this.drawTexturedModalRect(0,  0, 0, 0, 224, 256);
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation("enderpower", "textures/gui/gui_other.png"));
        if (isMouseOverTemp(mouseX, mouseY))
           this.drawTooltip(mouseX, mouseY, Collections.singletonList(this.container.base.getTemperatureTooltip()));

        if (isMouseOverEnergyCapacity(mouseX, mouseY))
            this.drawTooltip(mouseX, mouseY, Collections.singletonList(this.container.base.getEnergyCapacityTooltip()));

        int tempPosY = (int) this.container.base.temperature / 47;
        this.drawTexturedModalRect(84, 138 - tempPosY, 9, 0, 9, 5);

        int energyScaleY = (int) (this.container.base.stored / (this.container.base.maxCapacity / 128));
        this.drawTexturedModalRect(101, 140 - energyScaleY, 0, 128 - energyScaleY, 9, 128);

        this.drawTexturedModalRect(117, 10, 18, 0, 93, 41);

        this.fontRenderer.drawString(String.format("%s %s EU", I18n.format("erbigenerator.gui.stored_energy"), ModUtils.getString(this.container.base.stored)), 118, 55, 15659247, true);
        this.fontRenderer.drawString(String.format("%s %s EU", I18n.format("erbigenerator.gui.capacity_energy"), ModUtils.getString(this.container.base.maxCapacity)), 118, 63, 15659247, true);
        this.fontRenderer.drawString(String.format("%s %sC", I18n.format("erbigenerator.gui.temp"), this.container.base.temperature), 118, 71, 15659247, true);
        this.fontRenderer.drawString(String.format("%s %sC", I18n.format("erbigenerator.gui.max_temp"), this.container.base.maxTemperature), 118, 79, 15659247, true);
        this.fontRenderer.drawString(String.format("%s %s EU/t", I18n.format("erbigenerator.gui.prod"), ModUtils.getString(this.container.base.production)), 118, 87, 15659247, true);
        this.fontRenderer.drawString(String.format("%s %s EU/t", I18n.format("erbigenerator.gui.output"), I18n.format("erbigenerator.gui.infinity")), 118, 95, 15659247, true);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) throws IOException {
        super.actionPerformed(guibutton);
        IC2.network.get(false).initiateClientTileEntityEvent(this.container.base, guibutton.id);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.guiLeft + 30, this.guiTop + 60, 18, 18, ""));
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("enderpower", "textures/gui/gui_eg.png");
    }

    private final int xTemp = this.guiLeft+83;
    private final int yTemp = this.guiTop+12;

    private boolean isMouseOverTemp(int mouseX, int mouseY){
        return mouseX >= this.xTemp && mouseY >= this.yTemp && mouseX < this.xTemp+11 && mouseY < this.yTemp+134;
    }

    private final int xEnergy = this.guiLeft+101;
    private final int yEnergy = this.guiTop+12;

    private boolean isMouseOverEnergyCapacity(int mouseX, int mouseY) {
        return mouseX >= this.xEnergy && mouseY >= this.yEnergy && mouseX < this.xEnergy+9 && mouseY < this.yEnergy+128;
    }

    private final int xPrompt = this.guiLeft+196;
    private final int yPrompt = this.guiTop+233;

    private boolean isMouseOverPromt(int mouseX, int mouseY) {
        return mouseX >= this.xPrompt && mouseY >= this.yPrompt && mouseX < this.xPrompt+10 && mouseY < this.yPrompt+14;
    }
}
