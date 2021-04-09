package com.nofrfa.enderpower.tile.machines.destructor.gui;

import com.nofrfa.enderpower.tile.machines.destructor.DestructorTE;
import com.nofrfa.enderpower.tile.machines.gasconverter.GasConverterTE;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerDestr extends ContainerFullInv<DestructorTE> {
    public ContainerDestr(EntityPlayer player, DestructorTE tileEntity) {
        super(player, tileEntity, 166);
        addSlotToContainer(new SlotInvSlot(tileEntity.inputContainer, 0, 26, 35));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputContainer, 0, 116, 26));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputContainer, 1, 134, 26));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputContainer, 2, 116, 44));
        addSlotToContainer(new SlotInvSlot(tileEntity.outputContainer, 3, 134, 44));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("progress");
        return ret;
    }
}
