package com.nofrfa.enderpower.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class Shovel extends ItemSpade {
    public Shovel(ToolMaterial material, String name, CreativeTabs tab) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
