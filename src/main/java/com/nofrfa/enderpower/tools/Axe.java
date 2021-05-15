package com.nofrfa.enderpower.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class Axe extends ItemAxe {
    public Axe(ToolMaterial material, String name, CreativeTabs tab, float damage, float speed) {
        super(material, damage, speed);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}