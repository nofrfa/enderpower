package com.nofrfa.enderpower.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class Pickaxe extends ItemPickaxe {
    public Pickaxe(ToolMaterial material, String name, CreativeTabs tab) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }


}
