package com.nofrfa.enderpower.item.secondproducts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SecondProducts_0 extends Item {
    public SecondProducts_0(String name, int setMaxStack, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) { // Переопределение времени горения.
        return 39600; //200 - это кол-во тиков за 1 предмет. 400 - это 2 предмета, 600 - это 3 и так далее!
    }
}