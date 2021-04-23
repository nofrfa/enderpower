package com.nofrfa.enderpower.tile.machines.erbigen.gui;

import com.nofrfa.enderpower.tile.machines.erbigen.ErbiGeneratorTE;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerEG extends ContainerFullInv<ErbiGeneratorTE> {
    public ContainerEG(EntityPlayer player, ErbiGeneratorTE tileEntity) {
        super(player, tileEntity, 218, 256);
        addSlotToContainer(new SlotInvSlot(tileEntity.inputFluidSlot, 0, 23, 140));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputFluidSlot, 0, 45, 140));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("tier");
        ret.add("turn");
        ret.add("stored");
        ret.add("output");
        ret.add("maxCapacity");
        ret.add("production");
        ret.add("temperature");
        ret.add("maxTemperature");
        ret.add("fluidTank");
        return ret;
    }
}
