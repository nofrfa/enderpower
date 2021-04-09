package com.nofrfa.enderpower.tile.machines.erbigen;

import com.nofrfa.enderpower.misc.registr.FluidsRegister;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.tile.machines.quantumstorage.QuantumStorage;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.tile.IEnergyStorage;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.Fluids;
import ic2.core.block.comp.Redstone;
import ic2.core.block.comp.TileEntityComponent;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlotConsumableLiquid;
import ic2.core.block.invslot.InvSlotConsumableLiquidByTank;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.util.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class ErbiGeneratorTE_old extends TileEntityInventory implements INetworkClientTileEntityEventListener, IEnergyStorage, IHasGui, IWrenchable {
    protected Redstone redstone;
    private final int output;
    private final Energy energy;
    public int tier;
    public int generation;
    public int maxStorage;
    public int storage;
    public int temperature;
    public int maxTemperature;
    public int turn;
    public final FluidTank fluidTank;
    protected final Fluids fluids;
    public final InvSlotConsumableLiquid fluidSlot;
    public final InvSlotOutput fluidOut;
    public final InvSlot heatsinkSlots;
    public final InvSlot connector;
    public final InvSlotOutput secondproductsOut;

    public ErbiGeneratorTE_old() {
        this.redstone = this.addComponent(new Redstone(this));
        this.output = 2147483647;
        this.maxStorage = 150000000;
        this.storage = 0;
        this.turn = 0;
        this.temperature = 0;
        this.maxTemperature = 6000;
        this.tier = 4;
        this.energy = addComponent(new Energy(this, this.maxStorage, EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN, EnumFacing.UP, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH)),
                EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN)), this.tier
        ));
        this.fluids = (Fluids) addComponent((TileEntityComponent) new Fluids(this));
        this.fluidTank = this.fluids.addTank("fluidTank", 100000, Fluids.fluidPredicate(FluidsRegister.GAS_ERBI));
        this.fluidOut = new InvSlotOutput(this, "fluidOut", 1);
        this.fluidSlot = new InvSlotConsumableLiquidByTank(this, "fluidSlot", InvSlot.Access.I, 1, InvSlot.InvSide.ANY, InvSlotConsumableLiquid.OpType.Drain, this.fluidTank);
        this.heatsinkSlots = new InvSlot(this, "heatsinkSlots", InvSlot.Access.I, 3, InvSlot.InvSide.ANY);
        this.connector = new InvSlot(this, "connector", InvSlot.Access.IO, 1, InvSlot.InvSide.ANY);
        this.secondproductsOut = new InvSlotOutput(this, "secondProductsOut", 1, InvSlot.InvSide.ANY);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        if(this.energy.getEnergy() > this.energy.getCapacity()) {
            this.energy.addEnergy(this.energy.getEnergy() - this.energy.getCapacity());
        }

        if(this.redstone.getRedstoneInput() == 0)
            this.addEnergy(1000000);

        if(!this.connector.isEmpty() && this.connector.get().isItemEqual(new ItemStack(ItemsRegistry.MODULE_binder))) {
            int howManyEnergyOutput;

            if(this.energy.getEnergy() >= this.output)
                howManyEnergyOutput = this.output;
            else howManyEnergyOutput = (int) (this.output - (this.output - this.energy.getEnergy()));

            if (howManyEnergyOutput != 0) {
                int nbtX = Objects.requireNonNull(this.connector.get().getTagCompound()).getInteger("connectedX");
                int nbtY = Objects.requireNonNull(this.connector.get().getTagCompound()).getInteger("connectedY");
                int nbtZ = Objects.requireNonNull(this.connector.get().getTagCompound()).getInteger("connectedZ");

                BlockPos pos = new BlockPos(nbtX, nbtY, nbtZ);
                TileEntity tile = this.world.getTileEntity(pos);
                if (tile instanceof QuantumStorage) {
                    if(((QuantumStorage) tile).getStored() < ((QuantumStorage) tile).getCapacity()) {
                        double howManyFreeStorageEnergy = ((QuantumStorage) tile).getCapacity() - ((QuantumStorage) tile).getStored();
                        if(howManyFreeStorageEnergy < howManyEnergyOutput) {
                            double finalEnergyOutput = howManyEnergyOutput - (howManyEnergyOutput - howManyFreeStorageEnergy);
                            ((QuantumStorage) tile).addEnergy(finalEnergyOutput);
                            consumeEnergy(finalEnergyOutput);
                        } else {
                            ((QuantumStorage) tile).addEnergy(howManyEnergyOutput);
                            consumeEnergy(howManyEnergyOutput);
                        }
                    }
                }
            }
        }
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

    public int getStored() {
        return (int) this.energy.getEnergy();
    }

    public void setStored(int energy) {
    }

    public int addEnergy(int amount) {
        this.energy.addEnergy(amount);
        return amount;
    }

    public void consumeEnergy(double amount) {
        this.energy.useEnergy(amount);
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

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean canSetFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return null;
    }
}
