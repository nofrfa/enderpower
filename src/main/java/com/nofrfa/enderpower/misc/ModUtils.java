package com.nofrfa.enderpower.misc;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ModUtils {
    public static String getString(float number) {
        float g = number;
        float gg;
        int i = 0;

        for (; g >= 10; i++) {
            g = g / 10;
        }

        String returnString = "0";

        if (i >= 0 && i < 3 && number <= 1000) {
            gg = number;
            returnString = String.format("%.0f", gg);
        } else if (i >= 3 && i < 6 && number >= 1000 && number < 1000000) {
            gg = number / (1000);
            returnString = String.format("%.2fK", gg);
        } else if (i >= 6 && i < 9 && number >= 1000000 && number < 1000000000) {
            gg = number / (1000000);
            returnString = String.format("%.2fM", gg);
        } else if (i >= 9 && i < 12 && number >= 1000000000 && number < 2100000000) {
            gg = number / (1000000000);
            returnString = String.format("%.2fG", gg);
        }
        return returnString;
    }

    public static String getString(double number) {
        String returnString = "0";
        if (number <= 1000) {
            returnString = String.format("%.0f", number);
        } else if (number >= 10E2D && number < 10E5D) {
            returnString = String.format("%.2fK", number /10E2D);

        } else if (number >= 10E5D && number < 10E8D) {
            returnString = String.format("%.2fM", number /10E5D);

        } else if (number >= 10E8D && number < 10E11D) {
            returnString = String.format("%.2fG", number /10E8D);

        } else if (number >= 10E11D && number < 10E14D) {
            returnString = String.format("%.2fT", number /10E11D);

        } else if (number >= 10E14D && number < 10E17D) {
            returnString = String.format("%.2fP", number /10E14D);

        } else if (number >= 10E17D && number < 10E20D) {
            returnString = String.format("%.2fE", number /10E17D);

        } else if (number >= 10E20D && number < 10E23D) {
            returnString = String.format("%.2fZ", number /10E20D);

        } else if (number >= 10E23D && number < 10E26D) {
            returnString = String.format("%.2fY", number /10E23D);

        }
        return returnString;
    }

    public static final Item.ToolMaterial ToolMaterial_univ =
            EnumHelper.addToolMaterial(
                    "enderpower:tool", //name
                    4, //HarvestLevel
                    5712, //MaxUses
                    40.0F, //Efficiency
                    6.0F, //Damage
                    30 //Enchantability
            );
    public static final Item.ToolMaterial ToolMaterial_sword =
            EnumHelper.addToolMaterial(
                    "enderpower:sword", //name
                    4, //HarvestLevel
                    2856, //MaxUses
                    1.0F, //Efficiency
                    13.0F, //Damage
                    30 //Enchantability
            );
}
