package com.nofrfa.enderpower.tile.machines.quantumstorage;

import ic2.api.tile.IEnergyStorage;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import ic2.core.util.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

import java.util.EnumSet;

public class QuantumStorage_old extends TileEntityInventory implements IHasGui, IEnergyStorage {
    protected double output;
    public final Energy energy;

    public QuantumStorage_old() {
        this.output = 2147483647;
        this.energy = this.addComponent(new Energy(this, 2147483647,
                EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN)), EnumSet.of(EnumFacing.DOWN), 4));
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.of(getFacing())), EnumSet.of(getFacing()));
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void onPlaced(final ItemStack stack, final EntityLivingBase placer, final EnumFacing facing) {
        super.onPlaced(stack, placer, facing);
        if (!this.getWorld().isRemote) {
            final NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            this.energy.addEnergy(nbt.getDouble("energy"));
        }
    }

    @Override
    public int getOutput() {
        return (int)this.output;
    }

    @Override
    public double getOutputEnergyUnitsPerTick() {
        return this.output;
    }

    @Override
    public void setStored(final int energy) {
    }

    @Override
    public int addEnergy(final int amount) {
        this.energy.addEnergy(amount);
        return amount;
    }

    @Override
    public int getStored() {
        return (int)this.energy.getEnergy();
    }

    @Override
    public int getCapacity() {
        return (int)this.energy.getCapacity();
    }

    @Override
    public boolean isTeleporterCompatible(final EnumFacing side) {
        return true;
    }

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return null;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }
}

/*
public class QuantumStorage extends TileEntityInventory implements IEnergyStorage, IHasGui {
    public int capacity;
    public int output;
    private final Energy energy;
    public QuantumStorage() {
        this.capacity = 2147483647;
        this.output = 100000000;
        this.energy = addComponent(new Energy(this, this.capacity, EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH)),
                EnumSet.complementOf(EnumSet.of(EnumFacing.UP, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH)), 4
        ));
    }

    public void onPlaced(ItemStack stack, EntityLivingBase placer, EnumFacing facing) {
        super.onPlaced(stack, placer, facing);
        if (!(getWorld()).isRemote) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            this.energy.addEnergy(nbt.getDouble("energy"));
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.of(getFacing())), EnumSet.of(getFacing()));
        this.capacity = nbt.getInteger("capacity");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("capacity", this.capacity);
        return nbt;
    }

    @Override
    public int getStored() {
        return (int) this.energy.getEnergy();
    }

    @Override
    public void setStored(int amount) {
        this.energy.useEnergy(this.energy.getEnergy());
        this.energy.addEnergy(Math.min(amount, 2147483647));
    }

    @Override
    public int addEnergy(int amount) {
        this.energy.addEnergy(amount);
        return amount;
    }

    public int getCapacity() {
        return (int) this.energy.getCapacity();
    }

    public int getOutput() {
        return this.output;
    }

    public double getOutputEnergyUnitsPerTick() {
        return this.output;
    }

    public boolean isTeleporterCompatible(EnumFacing side) {
        return true;
    }

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerQS(entityPlayer, this);
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return new GuiQS(new ContainerQS(entityPlayer, this));
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }
}
 */