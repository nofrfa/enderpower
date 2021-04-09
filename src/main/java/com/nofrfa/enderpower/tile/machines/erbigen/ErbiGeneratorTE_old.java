package com.nofrfa.enderpower.tile.machines.erbigen;

import com.nofrfa.enderpower.misc.registr.FluidsRegister;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.tile.IEnergyStorage;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.Fluids;
import ic2.core.block.comp.Redstone;
import ic2.core.block.comp.TileEntityComponent;
import ic2.core.block.invslot.*;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidTank;

import java.util.EnumSet;

public class ErbiGeneratorTE_old extends TileEntityInventory implements INetworkClientTileEntityEventListener, IHasGui, IEnergyStorage {
    protected Redstone redstone;
    private final Energy energy;
    public int tier;
    public int output;
    public int generation;
    public int maxStorage;
    public int storage;
    public int temperature;
    public int maxTemperature;
    public int turn;
    public final FluidTank fluidTank;
    protected final Fluids fluids;
    public final InvSlotCharge chargeSlot;
    public final InvSlotConsumableLiquid fluidSlot;
    public final InvSlotOutput fluidOut;
    public final InvSlot heatsinkSlots;
    public final InvSlot connector;
    public final InvSlotOutput secondProductsOut;

    public ErbiGeneratorTE_old() {
        this.redstone = this.addComponent(new Redstone(this));
        this.output = 2147483647;
        this.maxStorage = 150000000;
        this.storage = 0;
        this.turn = 0;
        this.temperature = 0;
        this.maxTemperature = 6000;
        this.tier = 15;
        this.chargeSlot = new InvSlotCharge(this, 1);
        this.energy = this.addComponent(new Energy(this, maxStorage, EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN)), EnumSet.of(EnumFacing.DOWN), tier, tier, true).addManagedSlot(this.chargeSlot));
        this.fluids = (Fluids) addComponent((TileEntityComponent) new Fluids(this));
        this.fluidTank = this.fluids.addTank("fluidTank", 100000, Fluids.fluidPredicate(FluidsRegister.GAS_ERBI));
        this.fluidOut = new InvSlotOutput(this, "fluidOut", 1);
        this.fluidSlot = new InvSlotConsumableLiquidByTank(this, "fluidSlot", InvSlot.Access.I, 1, InvSlot.InvSide.ANY, InvSlotConsumableLiquid.OpType.Drain, this.fluidTank);
        this.heatsinkSlots = new InvSlot(this, "heatsinkSlots", InvSlot.Access.I, 3, InvSlot.InvSide.ANY);
        this.connector = new InvSlot(this, "connector", InvSlot.Access.IO, 1, InvSlot.InvSide.ANY);
        this.secondProductsOut = new InvSlotOutput(this, "secondProductsOut", 1, InvSlot.InvSide.ANY);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();

        if(this.energy.getEnergy() > this.energy.getCapacity()) {
            this.energy.addEnergy(this.energy.getEnergy() - this.energy.getCapacity());
        }

        if(this.redstone.getRedstoneInput() == 0)
            this.addEnergy(1000000);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.of(this.getFacing())), EnumSet.of(this.getFacing()));
        this.generation = nbt.getInteger("generation");
        this.maxStorage = nbt.getInteger("maxStorage");
        this.temperature = nbt.getInteger("temperature");
        this.maxTemperature = nbt.getInteger("maxTemperature");
        this.storage = nbt.getInteger("storage");
        this.turn = nbt.getInteger("turn");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("generation", this.generation);
        nbt.setInteger("maxStorage", this.maxStorage);
        nbt.setInteger("temperature", this.temperature);
        nbt.setInteger("maxTemperature", this.maxTemperature);
        nbt.setInteger("storage", this.getStored());
        nbt.setInteger("turn", this.turn);
        return nbt;
    }

    public void onNetworkUpdate(String field) {
        super.onNetworkUpdate(field);
        if (field.equals("turn"))
            setMode(this.turn);
    }

    @Override
    public void onNetworkEvent(EntityPlayer entityPlayer, int in) {
        if (in == 0)
            cycleMode();
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

    @Override
    public int getOutput() {
        return this.output;
    }

    @Override
    public double getOutputEnergyUnitsPerTick() {
        return this.output;
    }

    @Override
    public void setStored(final int energy) {
    }

    @Override
    public int addEnergy(int amount) {
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

    public void consumeEnergy(double amount) {
        this.energy.useEnergy(amount);
    }

    public int getMode() {
        return this.turn;
    }

    public void setMode(int mode1) {
        switch (mode1) {
            case 0:
                this.turn = 0;
                break;
            case 1:
                this.turn = 1;
                break;
            default:
                throw new RuntimeException("invalid mode: " + mode1);
        }
    }

    private void cycleMode() {
        setMode((getMode() + 1) % 2);
    }
}
