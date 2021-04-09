package com.nofrfa.enderpower.item;

import com.nofrfa.enderpower.tile.machines.quantumstorage.QuantumStorage;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Binder extends Item {
    public Binder(String name, int setMaxStack, CreativeTabs setTab) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(setTab);
        this.setMaxStackSize(setMaxStack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTagCompound()) {
            int nbtX = stack.getTagCompound().getInteger("connectedX");
            int nbtY = stack.getTagCompound().getInteger("connectedY");
            int nbtZ = stack.getTagCompound().getInteger("connectedZ");

            tooltip.add(I18n.format("tooltip.binder.connected") + " X: " + nbtX + " Y: " + nbtY + " Z: " + nbtZ);
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof QuantumStorage) {
                ItemStack item = player.getHeldItem(hand);

                NBTTagCompound inside = new NBTTagCompound();
                inside.setInteger("connectedX", pos.getX());
                inside.setInteger("connectedY", pos.getY());
                inside.setInteger("connectedZ", pos.getZ());

                item.setTagCompound(inside);
                return EnumActionResult.SUCCESS;
            }

            return EnumActionResult.FAIL;
        }
        return EnumActionResult.FAIL;
    }
}