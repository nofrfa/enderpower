package com.nofrfa.enderpower.tile.machines.quantumstorage.gui;

import com.nofrfa.enderpower.tile.machines.quantumstorage.QuantumStorage;
import ic2.core.ContainerFullInv;
import ic2.core.slot.ArmorSlot;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerQS extends ContainerFullInv<QuantumStorage> {
    public ContainerQS(EntityPlayer player, QuantumStorage tileEntity) {
        super(player, tileEntity, 166);
        for (int col = 0; col < ArmorSlot.getCount(); ++col) {
            this.addSlotToContainer(new SlotArmor(player.inventory, ArmorSlot.get(col), 8 + col * 18, 84));
        }
        this.addSlotToContainer(new SlotInvSlot(tileEntity.chargeSlot, 0, 56, 17));
        this.addSlotToContainer(new SlotInvSlot(tileEntity.dischargeSlot, 0, 56, 53));
    }

    public List<String> getNetworkedFields() {
        return super.getNetworkedFields();
    }
}
