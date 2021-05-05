package com.nofrfa.enderpower.item;

import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemList extends Item {
    public ItemList(String name, int setMaxStack) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TabsList.EXtabs);
        this.setMaxStackSize(setMaxStack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_energy))) {
            tooltip.add(I18n.format("upgrade.energy"));
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_capacity))) {
            tooltip.add(I18n.format("upgrade.capacity"));
        }

        if(stack.isItemEqual(new ItemStack(ItemsRegistry.UPGRADE_gift_energy))) {
            tooltip.add(I18n.format("upgrade.gift_energy"));
        }
    }
}