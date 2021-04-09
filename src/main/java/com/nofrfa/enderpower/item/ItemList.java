package com.nofrfa.enderpower.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemList extends Item {
    public ItemList(String name, int setMaxStack, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
    }
}