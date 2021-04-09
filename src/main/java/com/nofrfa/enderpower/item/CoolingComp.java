package com.nofrfa.enderpower.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class CoolingComp extends Item {
    public CoolingComp(String name, int setMaxStack, int maxDamage, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(maxDamage);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
        this.setNoRepair();
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack item = playerIn.getHeldItem(handIn);
        item.damageItem(20, playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }
}