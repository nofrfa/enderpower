package com.nofrfa.enderpower.tile.machines.gasconverter.gui;

import com.nofrfa.enderpower.tile.machines.gasconverter.GasConverterTE;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerGas extends ContainerFullInv<GasConverterTE> {
    public ContainerGas(EntityPlayer player, GasConverterTE tileEntity) {
        super(player, tileEntity, 166);
        addSlotToContainer(new SlotInvSlot(tileEntity.inputContainer, 0, 38, 35));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputContainer, 0, 56, 64));
        addSlotToContainer(new SlotInvSlot(tileEntity.inputFluidSlot, 0, 118, 21));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputFluidSlot, 0, 118, 53));
        //Кастомные апгреды
        addSlotToContainer(new SlotInvSlot(tileEntity.customUpgradeSlot1, 0, 154, 6));
        addSlotToContainer(new SlotInvSlot(tileEntity.customUpgradeSlot2, 0, 154, 24));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("progress");
        ret.add("fluidTank");
        return ret;
    }
}
