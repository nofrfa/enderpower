package com.nofrfa.enderpower.tile.machines.erbigen;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.tile.machines.erbigen.gui.ContainerEG;
import com.nofrfa.enderpower.tile.machines.erbigen.gui.GuiEG;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class ErbiGeneratorTE extends TileEntityInventory implements IEnergyTile, INetworkDataProvider, INetworkUpdateListener,
        IWrenchable, IEnergySource, IInventory, INetworkClientTileEntityEventListener, IHasGui {

    public int tier;
    public boolean turn;
    public double stored;
    public double maxCapacity;
    public double production;
    public double temperature;
    public double maxTemperature;
    public Energy energy;
    private boolean addedToEnet;

    public ErbiGeneratorTE() {
        this.tier = Configs.getErbiGeneratorTier();
        this.turn = false;
        this.maxCapacity = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity;
        this.stored = 0;
        this.production = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction;
        this.maxTemperature = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultMaxTemperature;
        this.temperature = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.tier = nbt.getInteger("tier");
        this.turn = nbt.getBoolean("turn");
        this.stored = nbt.getDouble("stored");
        this.maxCapacity = nbt.getDouble("maxCapacity");
        this.production = nbt.getDouble("production");
        this.temperature = nbt.getDouble("temperature");
        this.maxTemperature = nbt.getDouble("maxTemperature");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("tier", this.tier);
        nbt.setBoolean("turn", this.turn);
        nbt.setDouble("stored", this.stored);
        nbt.setDouble("maxCapacity", this.maxCapacity);
        nbt.setDouble("production", this.production);
        nbt.setDouble("temperature", this.temperature);
        nbt.setDouble("maxTemperature", this.maxTemperature);
        return nbt;
    }

    protected void onLoaded() {
        super.onLoaded();
        if (!this.world.isRemote)
            this.addedToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }

    @Override
    public void onPlaced(ItemStack stack, EntityLivingBase placer, EnumFacing facing) {
        super.onPlaced(stack, placer, facing);
        this.stored = 0;
        this.temperature = 0;
    }

    protected void onUnloaded() {
        super.onUnloaded();
        if (this.addedToEnet)
            this.addedToEnet = MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

    @Override
    protected void updateEntityServer() {
        super.updateEntityServer();

    }

    @Override
    public void onNetworkEvent(EntityPlayer entityPlayer, int i) {
        if (i == 0)
            cycleMode();
    }

    @Override
    public double getOfferedEnergy() {
        return Math.min(this.production, this.stored);
    }

    @Override
    public void drawEnergy(double v) {
        this.stored -= (int) v;
    }

    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return true;
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return null;
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

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerEG(entityPlayer, this);
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return new GuiEG(new ContainerEG(entityPlayer, this));
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    @Override
    public String getName() {
        return null;
    }

    public String getMode() {
        return this.turn ? "on" : "off";
    }

    public void setMode(int mode1) {
        switch (mode1) {
            case 0:
                this.turn = false;
                break;
            case 1:
                this.turn = true;
                break;
            default:
                throw new RuntimeException("invalid mode: " + mode1);
        }
    }

    private void cycleMode() {
        setMode(this.turn ? 0 : 1);
    }
}
