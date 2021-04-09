package com.nofrfa.enderpower.tile.machines.erbigen.gui;

import com.nofrfa.enderpower.tile.machines.erbigen.ErbiGeneratorTE;
import ic2.core.ContainerFullInv;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerEG extends ContainerFullInv<ErbiGeneratorTE> {
    public ContainerEG(EntityPlayer player, ErbiGeneratorTE tileEntity) {
        super(player, tileEntity, 186);
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        return ret;
    }
}
