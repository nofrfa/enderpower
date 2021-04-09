package com.nofrfa.enderpower.item.upgrade;

import com.nofrfa.enderpower.misc.Configs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class MachinesUpgradeVolecy extends Item {
    public MachinesUpgradeVolecy(String name, int setMaxStack, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int boostEnergy = stack.getCount() * Configs.GeneralSettings.Upgrades.Volecy.upgrades_volecy_increaseEnergyConsume;
        int boostMilibackets = stack.getCount() * Configs.GeneralSettings.Upgrades.Volecy.upgrades_volecy_mbBoost;

        tooltip.add(I18n.format("upgrade.volecy") + " " + boostEnergy + " " + I18n.format("more.eu_t"));
        tooltip.add(I18n.format("upgrade.volecy2") + " " + boostMilibackets + " " + I18n.format("more.mb_out"));
        tooltip.add(I18n.format("upgrade.alert"));
    }
}