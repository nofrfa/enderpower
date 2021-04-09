package com.nofrfa.enderpower.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ErbiAmulet extends Item {
    public ErbiAmulet(String name, int setMaxStack, int maxDamage, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
        this.setMaxDamage(maxDamage);
        this.setNoRepair();
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
}