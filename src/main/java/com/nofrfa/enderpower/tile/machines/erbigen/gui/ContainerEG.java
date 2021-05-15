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
        addSlotToContainer(new SlotInvSlot(tileEntity.outputSecondProducts, 0, 158, 117));

        addSlotToContainer(new SlotInvSlot(tileEntity.heatSink, 0, 34, 111));

        int i;
        for(i = 0; i < 3; i++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.heatSink_reserve, i, 16 + 18 * i, 64));
        }
        for(i = 0; i < 3; i++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.heatSink_reserve, 3 + i, 16 + 18 * i, 82));
        }

        for(i = 0; i < 3; i++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.upgrades, i, 16 + 18 * i, 16));
        }
        for(i = 0; i < 3; i++) {
            addSlotToContainer(new SlotInvSlot(tileEntity.upgrades, 3 + i, 16 + 18 * i, 34));
        }
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("tier");
        ret.add("turn");
        ret.add("stored");
        ret.add("output");
        ret.add("maxCapacity");
        ret.add("guiProd");
        ret.add("temperature");
        ret.add("maxTemperature");
        ret.add("fluidTank");
        ret.add("giftEnergy");
        return ret;
    }
}
