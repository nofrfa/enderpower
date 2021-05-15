package com.nofrfa.enderpower.item;

import com.nofrfa.enderpower.misc.tabs.TabsList;
import ic2.core.init.Localization;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CoolingComp extends Item {
    public CoolingComp(String name, int setMaxStack, int maxDamage) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(maxDamage);
        this.setCreativeTab(TabsList.EXtabs);
        this.setMaxStackSize(setMaxStack);
        this.setNoRepair();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(!stack.isItemDamaged())
            tooltip.add(String.format("%s %s / %s", Localization.translate("ic2.reactoritem.durability"), stack.getMaxDamage() - stack.getItemDamage(), stack.getMaxDamage()));
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }
}