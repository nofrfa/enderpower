package com.nofrfa.enderpower.tile.machines.erbigen;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.registr.FluidsRegister;
import com.nofrfa.enderpower.tile.machines.erbigen.gui.ContainerEG;
import com.nofrfa.enderpower.tile.machines.erbigen.gui.GuiEG;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.Fluids;
import ic2.core.block.comp.TileEntityComponent;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlotConsumableLiquid;
import ic2.core.block.invslot.InvSlotConsumableLiquidByList;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.init.Localization;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ErbiGeneratorTE extends TileEntityInventory implements INetworkDataProvider, INetworkUpdateListener,
        IWrenchable, IEnergySource, IInventory, INetworkClientTileEntityEventListener, IHasGui {

    public int tier;
    public boolean turn;
    public double stored;
    public double maxCapacity;
    public double production;
    public double output;
    public double temperature;
    public double maxTemperature;
    public Energy energy;
    private boolean addedToEnet;
    private int timer;
    public final FluidTank fluidTank;
    protected final Fluids fluids;
    public final InvSlotOutput outputFluidSlot;
    public final InvSlotConsumableLiquid inputFluidSlot;
    public final InvSlot heatSink_reserve;
    public final InvSlot heatSink;

    public ErbiGeneratorTE() {
        this.tier = Configs.getErbiGeneratorTier();
        this.turn = false;
        this.maxCapacity = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity;
        this.stored = 0;
        this.production = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction;
        this.output = 9_223_923_928_372_036_854_807D;
        this.maxTemperature = 6000;
        this.temperature = 0;
        this.fluids = (Fluids) addComponent((TileEntityComponent) new Fluids(this));
        this.fluidTank = this.fluids.addTank("fluidTank", 100000, Fluids.fluidPredicate(FluidsRegister.GAS_ERBI));
        this.outputFluidSlot = new InvSlotOutput(this, "fluid_output", 1);
        this.inputFluidSlot = new InvSlotConsumableLiquidByList(this, "fluid_input", InvSlot.Access.I, 1, InvSlot.InvSide.ANY, InvSlotConsumableLiquid.OpType.Drain, FluidsRegister.GAS_ERBI);
        this.heatSink_reserve = new InvSlot(this, "heatSink_reserve",  InvSlot.Access.IO, 3);
        this.heatSink = new InvSlot(this, "heatSink",  InvSlot.Access.O, 1);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.tier = nbt.getInteger("tier");
        this.turn = nbt.getBoolean("turn");
        this.stored = nbt.getDouble("stored");
        this.output = nbt.getDouble("output");
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
        nbt.setDouble("output", this.output);
        nbt.setDouble("maxCapacity", this.maxCapacity);
        nbt.setDouble("production", this.production);
        nbt.setDouble("temperature", this.temperature);
        nbt.setDouble("maxTemperature", this.maxTemperature);
        return nbt;
    }

    @Override
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, tooltip, advanced);
        tooltip.add(Localization.translate("ic2.item.tooltip.PowerTier", this.tier));
        tooltip.add(String.format("%s %s",
                Localization.translate("ic2.item.tooltip.Capacity"),
                ModUtils.getString(this.maxCapacity)
        ));
        tooltip.add(String.format("%s %s EU/t",
                Localization.translate("ic2.item.tooltip.Output"),
                ModUtils.getString(this.output)
        ));
    }

    protected void onLoaded() {
        super.onLoaded();
        if (!this.world.isRemote) {
            this.addedToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        }
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

        if(this.timer++ % 100 == 0) {
            if(this.temperature <= 5900)
                this.temperature += 100;

            if(this.maxCapacity != Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity)
                this.maxCapacity = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity;

            if(this.production != Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction)
                this.production = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction;

            if(this.stored > this.maxCapacity)
                this.stored = this.maxCapacity;
        }

        if(this.timer >= 100)
            this.timer = 0;

        if (this.inputFluidSlot.processIntoTank(this.fluidTank, this.outputFluidSlot))
            this.markDirty();

        if(this.stored <= this.maxCapacity - this.production)
        this.stored += this.production;
    }

    @Override
    public void onNetworkEvent(EntityPlayer player, int i) {
        if (i == 0)
            cycleMode();
    }

    @Override
    public double getOfferedEnergy() {
        return Math.min(this.output, this.stored);
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
    public ContainerBase<?> getGuiContainer(EntityPlayer player) {
        return new ContainerEG(player, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean b) {
        return new GuiEG((new ContainerEG(player, this)));
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    @Override
    public String getName() {
        return null;
    }

    public String getTemperatureTooltip() {
        return String.format("%sC / %sC", this.temperature, this.maxTemperature);
    }

    public String getEnergyCapacityTooltip() {
        return String.format("%s EU / %s EU", ModUtils.getString(this.stored), ModUtils.getString(this.maxCapacity));
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
