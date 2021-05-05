package com.nofrfa.enderpower.tile.machines.erbigen;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.registr.FluidsRegister;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
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
import ic2.core.block.invslot.*;
import ic2.core.init.Localization;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import java.util.concurrent.ThreadLocalRandom;

public class ErbiGeneratorTE extends TileEntityInventory implements INetworkDataProvider, INetworkUpdateListener,
        IWrenchable, IEnergySource, INetworkClientTileEntityEventListener, IHasGui {

    public int tier;
    public boolean turn;
    public double stored;
    public double maxCapacity;
    public double production;
    public double guiProd;
    public double output;
    public double temperature;
    public double maxTemperature;
    public Energy energy;
    private boolean addedToEnet;
    private int timer;
    public double workTime;
    public double giftEnergy;
    public final FluidTank fluidTank;
    protected final Fluids fluids;
    public final InvSlotOutput outputFluidSlot;
    public final InvSlotConsumableLiquid inputFluidSlot;
    public final InvSlotConsumableItemStack heatSink_reserve;
    public final InvSlotConsumableItemStack heatSink;
    public static ItemStack[] upgradesList = {
            new ItemStack(ItemsRegistry.UPGRADE_energy),
            new ItemStack(ItemsRegistry.UPGRADE_capacity),
            new ItemStack(ItemsRegistry.UPGRADE_gift_energy)
    };
    public final InvSlotConsumableItemStack upgrades;


    public ErbiGeneratorTE() {
        this.tier = Configs.getErbiGeneratorTier();
        this.turn = false;
        this.maxCapacity = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity;
        this.stored = 0;
        this.production = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction;
        this.guiProd = 0;
        this.output = 9_223_923_928_372_036_854_807D;
        this.maxTemperature = 6000;
        this.temperature = 0;
        this.workTime = 0;
        this.giftEnergy = 0;
        this.fluids = (Fluids) addComponent((TileEntityComponent) new Fluids(this));
        this.fluidTank = this.fluids.addTank("fluidTank", 100000, Fluids.fluidPredicate(FluidsRegister.GAS_ERBI));
        this.outputFluidSlot = new InvSlotOutput(this, "fluid_output", 1);
        this.inputFluidSlot = new InvSlotConsumableLiquidByList(this, "fluid_input", InvSlot.Access.I, 1, InvSlot.InvSide.ANY, InvSlotConsumableLiquid.OpType.Drain, FluidsRegister.GAS_ERBI);
        ItemStack[] heatSinks = {
                new ItemStack(ItemsRegistry.COMPONENT_1),
                new ItemStack(ItemsRegistry.COMPONENT_2),
                new ItemStack(ItemsRegistry.COMPONENT_3),
                new ItemStack(ItemsRegistry.COMPONENT_4),
                new ItemStack(ItemsRegistry.COMPONENT_5),
                new ItemStack(ItemsRegistry.COMPONENT_6),
                new ItemStack(ItemsRegistry.COMPONENT_7),
        };
        this.heatSink_reserve = new InvSlotConsumableItemStack(this, "heatSink_reserve",  InvSlot.Access.IO, 6, InvSlot.InvSide.ANY, heatSinks);
        this.heatSink = new InvSlotConsumableItemStack(this, "heatSink",  InvSlot.Access.O, 1, InvSlot.InvSide.NOTSIDE);
        this.upgrades = new InvSlotConsumableItemStack(this, "upgrades", InvSlot.Access.IO, 6, InvSlot.InvSide.ANY, upgradesList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.tier = nbt.getInteger("tier");
        this.turn = nbt.getBoolean("turn");
        this.stored = nbt.getDouble("stored");
        this.output = nbt.getDouble("output");
        this.maxCapacity = nbt.getDouble("maxCapacity");
        this.guiProd = nbt.getDouble("guiProd");
        this.temperature = nbt.getDouble("temperature");
        this.maxTemperature = nbt.getDouble("maxTemperature");
        this.giftEnergy = nbt.getDouble("giftEnergy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("tier", this.tier);
        nbt.setBoolean("turn", this.turn);
        nbt.setDouble("stored", this.stored);
        nbt.setDouble("output", this.output);
        nbt.setDouble("maxCapacity", this.maxCapacity);
        nbt.setDouble("guiProd", this.guiProd);
        nbt.setDouble("temperature", this.temperature);
        nbt.setDouble("maxTemperature", this.maxTemperature);
        nbt.setDouble("giftEnergy", this.giftEnergy);
        return nbt;
    }

    @Override
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, tooltip, advanced);
        tooltip.add(Localization.translate("ic2.item.tooltip.PowerTier", this.tier));
        tooltip.add(String.format("%s %s EU",
                Localization.translate("ic2.item.tooltip.Capacity"),
                ModUtils.getString(this.maxCapacity)
        ));
        tooltip.add(String.format("%s %s EU/t",
                Localization.translate("ic2.item.tooltip.Output"),
                I18n.format("erbigenerator.gui.infinity")
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
        this.workTime = 0;
    }

    protected void onUnloaded() {
        super.onUnloaded();
        if (this.addedToEnet)
            this.addedToEnet = MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

    @Override
    protected void onBlockBreak() {
        super.onBlockBreak();
        //this.world.newExplosion(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 20.0F, true, true);
    }

    @Override
    protected void updateEntityServer() {
        super.updateEntityServer();

        double energyProdBonus = 0;
        double capacityBonus = 0;
        double giftEnergyBonus = 0;

        for(int i = 0; i < this.upgrades.size(); i++) {
            for(int i1 = 0; i1 < upgradesList.length; i1++) {
                if(this.upgrades.get(i).isItemEqual(upgradesList[i1])) { // 0 - Energy | 1 - Capacity | 2 - GiftEnergy
                    switch (i1) {
                        case 0:
                            energyProdBonus += Configs.GeneralSettings.Upgrades.Energy.energy_upgrade_boost;
                            break;
                        case 1:
                            capacityBonus += Configs.GeneralSettings.Upgrades.Capacity.capacity_upgrade_boost;
                            break;
                        case 2:
                            giftEnergyBonus += Configs.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_boost;
                            break;
                    }
                }
            }
        }

        if(this.timer++ % 100 == 0) {
            if(this.maxCapacity != Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity + energyProdBonus)
                this.maxCapacity = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultEnergyCapacity + energyProdBonus;

            if(this.production != Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction + energyProdBonus)
                this.production = Configs.GeneralSettings.Mechanisms.Erbi_Generator.defaultProduction + energyProdBonus;

            if(this.stored > this.maxCapacity)
                this.stored = this.maxCapacity;
        }

        if(this.timer >= 100)
            this.timer = 0;

        if(this.heatSink.isEmpty()) {
            for(int i = 0; i < 6; i ++) {
                if(!this.heatSink_reserve.isEmpty(i)) {
                    this.heatSink.put(this.heatSink_reserve.get(i).copy());
                    this.heatSink_reserve.clear(i);
                    break;
                }
            }
        }

        if(this.inputFluidSlot.processIntoTank(this.fluidTank, this.outputFluidSlot))
            this.markDirty();

        if(this.fluidTank.getFluidAmount() <= 0)
            this.setMode(0);

        if(this.getMode()) {
            this.setActive(true);
            this.workTime += 1;
            if(this.temperature < 2700) {
                if(this.timer % 5 == 0) {
                    this.fluidTank.drain(1, true);
                    this.temperature += getRandom(5, 9);
                }
            } else {
                if(this.timer % 10 == 0) {
                    this.fluidTank.drain(1, true);
                    this.temperature += Math.min(this.maxTemperature - this.temperature, getRandom(3, 9));

                    if(!this.heatSink.isEmpty()) {
                        int excessTemp = (int) this.temperature - 2700;
                        int durabilityHeatSink = this.heatSink.get().getMaxDamage() - this.heatSink.get().getItemDamage();

                        int tmp = Math.min(excessTemp, Math.min(durabilityHeatSink, 10));
                        this.heatSink.damage(tmp, false);
                        this.temperature -= tmp;
                    }
                }
            }
        } else {
            this.setActive(false);
            this.temperature -= Math.min(this.temperature, getRandom(0, 6));
            if(this.temperature < 1000) {
                this.guiProd = 0;
            }
            this.workTime = 0;
            this.giftEnergy = 0;
        }

        if(this.workTime == 4100) {
            double tmp = this.production + energyProdBonus + this.giftEnergy + giftEnergyBonus;
            this.giftEnergy += getRandom(
                    tmp / Configs.GeneralSettings.Mechanisms.Erbi_Generator.gift_division_1,
                    tmp / Configs.GeneralSettings.Mechanisms.Erbi_Generator.gift_division_2
            );
            this.workTime = 0;
        }

        if(this.temperature >= 1000) {
            double energyPerTemp = ((this.production + energyProdBonus) / 1700) * (this.temperature - 1000);
            this.guiProd = energyPerTemp;
            this.stored += Math.min(getFreeEnergy(), energyPerTemp) + this.giftEnergy;
        }
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
        return String.format("%sC / %sC", String.format("%.2f", this.temperature), this.maxTemperature);
    }

    public String getEnergyCapacityTooltip() {
        return String.format("%s EU / %s EU", ModUtils.getString(this.stored), ModUtils.getString(this.maxCapacity));
    }

    public boolean getMode() {
        return this.turn;
    }

    public double getFreeEnergy() {
        return this.maxCapacity - this.stored;
    }

    private double getRandom(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
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
