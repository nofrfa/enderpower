package com.nofrfa.enderpower.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class GasBlockErbi extends BlockFluidClassic {
    public GasBlockErbi(Fluid fluid) {
        super(fluid, new MaterialErbi(MapColor.WATER));
        setRegistryName("gas_erbi");
    }
}
