package com.nofrfa.enderpower.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialErbi extends Material {
    public MaterialErbi(MapColor color)
    {
        super(color);
        this.setReplaceable();
        this.setNoPushMobility();
    }

    public boolean isLiquid()
    {
        return true;
    }

    public boolean blocksMovement()
    {
        return false;
    }

    public boolean isSolid()
    {
        return false;
    }
}
