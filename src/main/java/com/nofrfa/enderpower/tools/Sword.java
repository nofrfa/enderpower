package com.nofrfa.enderpower.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class Sword extends ItemSword {
    public Sword(ToolMaterial material, String name, CreativeTabs tab) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
