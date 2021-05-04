package com.nofrfa.enderpower.tile.machines.destructor;

import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import com.nofrfa.enderpower.tile.machines.destructor.gui.ContainerDestr;
import com.nofrfa.enderpower.tile.machines.destructor.gui.GuiDestr;
import ic2.api.tile.IWrenchable;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.comp.Redstone;
import ic2.core.block.invslot.InvSlotConsumableItemStack;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.profile.NotClassic;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@NotClassic
public class DestructorTE extends TileEntityElectricMachine implements IHasGui, IWrenchable {
    protected Redstone redstone;
    public final InvSlotConsumableItemStack inputContainer;
    public final InvSlotOutput outputContainer;
    private int MAX_PROGRESS;
    private float progress;
    private int energyConsume;
    private int timer;

    public static ItemStack[] inputItem = {
            is(ItemsRegistry.DUST_spadiy, 1),
            is(ItemsRegistry.GENERATOR_sp_2, 4)
    };
    public static ItemStack[][] outputItem = {
            {is(ItemsRegistry.INGOT_spadiy, 1)},
            {is(ItemsRegistry.GENERATOR_sp_3, 1)}
    };

    public DestructorTE() {
        super(16000, 4);
        this.redstone = this.addComponent(new Redstone(this));
        this.inputContainer = new InvSlotConsumableItemStack(this, "in", 1, inputItem);
        this.outputContainer = new InvSlotOutput(this, "out", 4);
        this.progress = 0;
        this.MAX_PROGRESS = 600;
        this.energyConsume = Configs.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progress = nbt.getInteger("progress");
        this.MAX_PROGRESS = nbt.getInteger("max_progress");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", getProgress());
        nbt.setInteger("max_progress", this.MAX_PROGRESS);
        return nbt;
    }

    protected void updateEntityServer() {
        super.updateEntityServer();

        if(this.timer++ % 100 == 0) {
            if(this.energyConsume != Configs.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume)
                this.energyConsume = Configs.GeneralSettings.Mechanisms.Destructor.defaultEnergyConsume;
        }

        if(this.timer >= 100)
            this.timer = 0;

        boolean isActive = this.redstone.getRedstoneInput() == 0;
        int recipeNumber = 0;

        if(!this.inputContainer.isEmpty()) {
            for (int index = 0; index < inputItem.length; index++) {
                if (this.inputContainer.get().isItemEqual(inputItem[index])) {
                    recipeNumber = index;
                    break;
                }
            }
        }

        if(this.inputContainer.isEmpty()) this.progress = 0;

        if(canWork(recipeNumber) && canOut(recipeNumber) && isActive) {
            this.progress++;
            this.energy.useEnergy(energyConsume);
            this.setActive(true);
        } else this.setActive(false);

        if(this.progress == this.MAX_PROGRESS) {
            this.progress = 0;
            completed(recipeNumber);
        }
    }

    private void completed(int recipeNumber) {
        this.inputContainer.consume(inputItem[recipeNumber].getCount());

        for(int index = 0; index < outputItem[recipeNumber].length; index++) {
            this.outputContainer.add(outputItem[recipeNumber][index]);
        }
    }

    private boolean canWork(int recipeNumber) {
        return !this.inputContainer.isEmpty()
                && this.energy.canUseEnergy(energyConsume)
                && this.inputContainer.get().getCount() >= inputItem[recipeNumber].getCount();
    }

    // TODO: 15.03.2021 rework check logic
    private boolean canOut(int recipeNumber) {
        int checkID = 0;
        for(int index = 0; index < outputItem[recipeNumber].length; index++) {
            if(this.outputContainer.canAdd(outputItem[recipeNumber][index]))
                checkID++;
        }
        return checkID == outputItem[recipeNumber].length;
    }

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerDestr(entityPlayer, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return new GuiDestr(new ContainerDestr(entityPlayer, this));
    }

    public int getProgress() {
        return Math.round((this.progress / this.MAX_PROGRESS) * 100F);
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return this.getFacing();
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        if (!this.canSetFacingWrench(enumFacing, entityPlayer)) {
            return false;
        } else {
            this.setFacing(enumFacing);
            return true;
        }
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        List<ItemStack> list = new ArrayList<>();
        inputContainer.forEach(list::add);
        outputContainer.forEach(list::add);

        return list;
    }

    private static ItemStack is(Item item, int amount){
        return new ItemStack(item, amount);
    }

    private static ItemStack is(Block item, int amount){
        return new ItemStack(item, amount);
    }
}