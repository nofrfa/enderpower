package com.nofrfa.enderpower.tile.machines.quantumstorage;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.tile.machines.quantumstorage.gui.ContainerQS;
import com.nofrfa.enderpower.tile.machines.quantumstorage.gui.GuiQS;
import ic2.core.ContainerBase;
import ic2.core.block.wiring.TileEntityElectricBlock;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class QuantumStorage extends TileEntityElectricBlock {
    public QuantumStorage() {
        super(Configs.getQuantumTier(), 10000000, 2147483647);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        if (this.energy.getEnergy() > 0) {
            this.energy.useEnergy(this.chargeSlot.charge(this.energy.getEnergy()));
        }
    }

    @Override
    public ContainerBase<? extends TileEntityElectricBlock> getGuiContainer(EntityPlayer player) {
        return new ContainerQS(player, this);
    }

    @Override
    public GuiScreen getGui(EntityPlayer player, boolean isAdmin) {
        return new GuiQS(new ContainerQS(player, this));
    }
}