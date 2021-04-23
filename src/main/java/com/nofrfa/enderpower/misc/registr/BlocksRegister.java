package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.blocks.BlockList;
import com.nofrfa.enderpower.fluids.GasBlockErbi;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlocksRegister {
    /* [1]
    Добавляем
    public static Block ВАШЕ_НАЗВАНИЕ = new BlockList("имя_ТОЛЬКО_МАЛЕНЬКИЕ_СИМВОЛЫ", Material.материал, прозрачность_, время_ломания, взрывоустройчивость, предмет_которым_можно_сломать, уровень_инструмента, вкладка_в_креативе);

    прозрачность_ -- если вы делаете прозрачный блок ставьте 0, если непрозрачный ставьте 255

    Уровени инструмента:
    Деревянные  0
    Каменные    1
    Железные    2
    Алмазные    3
    Золотые     0
    */
    /*
    [[[Material.материал]]]

      AIR         GRASS
      GROUND      WOOD
      ROCK        IRON
      ANVIL       WATER
      LAVA        LEAVES
      PLANTS      VINE
      SPONGE      CLOTH
      FIRE        SAND
      CIRCUITS    CARPET
      GLASS       REDSTONE_LIGHT
      TNT         CORAL
      ICE         PACKED_ICE
      SNOW        CRAFTED_SNOW
      CACTUS      CLAY
      GOURD       DRAGON_EGG
      PORTAL      CAKE
      WEB
    */
    /*
    Пример: public static Block URANIUM_CHILD = new BlockList("uran_child", Material.IRON, 255, 3.0F, 100.0F, "pickaxe", 3, TabsList.EXtabs);
    Примечание: Вы можете добавлять блоки точно так же, как и броню - через запятую
    */

    public static Block
            BLOCK_UltimateMachineCasing = new BlockList("ultimate_machine_casing", Material.IRON, 255, 10.0F, 100.0F, "pickaxe", 3, TabsList.EXtabs),
            GAS_ERBI = new GasBlockErbi(FluidsRegister.GAS_ERBI)


    ;


    public static void register() {
        /* [2]
        Добавляем
        setRegister(ВАШЕ_НАЗВАНИЕ_которое_писали_выше);
        Пример: setRegister(URANIUM_CHILD);
        */
        setRegister(BLOCK_UltimateMachineCasing);
        setRegister(GAS_ERBI);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        /* [3]
        Добавляем
        setRender(ВАШЕ_НАЗВАНИЕ_которое_писали_выше);
        Пример: setRender(URANIUM_CHILD);
        */
        setRender(BLOCK_UltimateMachineCasing);
        setRender(GAS_ERBI);
    }

    // ВСЁ ЧТО НИЖЕ - НЕ ТРОГАЕМ
    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}