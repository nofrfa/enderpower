package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.misc.RarityList;
import com.nofrfa.enderpower.tile.machines.destructor.DestructorTE;
import com.nofrfa.enderpower.tile.machines.erbigen.ErbiGeneratorTE;
import com.nofrfa.enderpower.tile.machines.gasconverter.GasConverterTE;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.Set;

public enum AllMachinesTE implements ITeBlock {
    gas_converter(GasConverterTE.class, 0, RarityList.defaultRarity),
    destructor(DestructorTE.class, 1, RarityList.defaultRarity),
    erbi_generator(ErbiGeneratorTE.class, 2, RarityList.defaultRarity);

    private final Class AMTEClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;
    private static final AllMachinesTE[] VALUES = values();
    public static final ResourceLocation LOCATIONRES;

    static {
        LOCATIONRES = new ResourceLocation(EnderPower.MODID, "machines");
    }

    private AllMachinesTE(Class AMTEClass, int itemMeta, EnumRarity rarity) {
        this.AMTEClass = AMTEClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
        GameRegistry.registerTileEntity(AMTEClass, "enderpower:" + this.getName());
    }

    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }

    @Nonnull
    public ResourceLocation getIdentifier() {
        return LOCATIONRES;
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Override
    @Nonnull
    public Class getTeClass() {
        return this.AMTEClass;
    }

    @Override
    public boolean hasActive() {
        return true;
    }

    @Override
    @Nonnull
    public Set<net.minecraft.util.EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
    }

    @Override
    public float getHardness() {
        return 5.0F;
    }

    @Override
    public float getExplosionResistance() {
        return 0.0F;
    }

    @Override
    @Nonnull
    public TeBlock.HarvestTool getHarvestTool() {
        return TeBlock.HarvestTool.Pickaxe;
    }

    @Override
    @Nonnull
    public TeBlock.DefaultDrop getDefaultDrop() {
        return TeBlock.DefaultDrop.Self;
    }

    @Override
    @Nonnull
    public EnumRarity getRarity() {
        return this.rarity;
    }

    @Override
    public boolean allowWrenchRotating() {
        return true;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.itemMeta;
    }

    @Nonnull
    public Material getMaterial() {
        return Material.ROCK;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    public static void buildDummies() {
        ModContainer main = Loader.instance().activeModContainer();
        if(main != null && EnderPower.MODID.equals(main.getModId())) {
            AllMachinesTE[] var1 = VALUES;
            int var2 = var1.length;

            for(int var3 = 1; var3 < var2; ++var3) {
                AllMachinesTE block = var1[var3];
                if(block.AMTEClass != null) {
                    try {
                        block.dummyTe = (TileEntityBlock) block.AMTEClass.newInstance();
                    } catch (Exception var6) {
                        if(Util.inDev()) { var6.printStackTrace(); }
                    }
                }
            }
        } else { throw new IllegalAccessError("accessErrorLegacy ::::"); }
    }
}
