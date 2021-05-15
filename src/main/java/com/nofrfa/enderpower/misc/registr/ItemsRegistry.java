package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.EnderPower;
import com.nofrfa.enderpower.item.*;
import com.nofrfa.enderpower.item.secondproducts.SecondProducts_0;
import com.nofrfa.enderpower.item.secondproducts.SecondProducts_1;
import com.nofrfa.enderpower.item.upgrade.MachinesUpgradeSpeed;
import com.nofrfa.enderpower.item.upgrade.MachinesUpgradeVolecy;
import com.nofrfa.enderpower.misc.Configs;
import com.nofrfa.enderpower.misc.ModUtils;
import com.nofrfa.enderpower.misc.tabs.TabsList;
import com.nofrfa.enderpower.tools.Axe;
import com.nofrfa.enderpower.tools.Pickaxe;
import com.nofrfa.enderpower.tools.Shovel;
import com.nofrfa.enderpower.tools.Sword;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(EnderPower.MODID)
@Mod.EventBusSubscriber
public class ItemsRegistry {
    //Сдерживатель
    @GameRegistry.ObjectHolder("deterrent")
    public static final Item ITEM_deterrent = null;

    //Сдерживание (заполнен)
    @GameRegistry.ObjectHolder("deterrent_filled")
    public static final Item ITEM_deterrent_filled = null;

    //Слиток спадия
    @GameRegistry.ObjectHolder("spadiy")
    public static final Item INGOT_spadiy = null;

    //Неифрит
    @GameRegistry.ObjectHolder("neifrit")
    public static final Item INGOT_nefrit = null;

    //Эрбий
    @GameRegistry.ObjectHolder("erbi")
    public static final Item INGOT_erbi = null;

    //Смесь спадия
    @GameRegistry.ObjectHolder("spadiy_dust")
    public static final Item DUST_spadiy = null;

    //Стальная пыль
    @GameRegistry.ObjectHolder("steel_dust")
    public static final Item DUST_steel = null;

    //Неифритовая пыль
    @GameRegistry.ObjectHolder("neifrit_dust")
    public static final Item DUST_neifrit = null;

    //Неифритовая пластина
    @GameRegistry.ObjectHolder("neifrit_plate")
    public static final Item PLATE_neifrit = null;

    //Пластина из спадия
    @GameRegistry.ObjectHolder("spadiy_plate")
    public static final Item PLATE_spadiy = null;

    //Неифритовая оболочка
    @GameRegistry.ObjectHolder("neifrit_casing")
    public static final Item CASING_neifrit = null;

    //Неифритовая пластина плотная
    @GameRegistry.ObjectHolder("neifrit_plate_dense")
    public static final Item PLATE_neifrit_compres = null;

    //Спадиевая пластина плотная
    @GameRegistry.ObjectHolder("spadiy_plate_dense")
    public static final Item PLATE_spadiy_compres = null;

    //Сжатый углепластик
    @GameRegistry.ObjectHolder("compressed_carbon_plate")
    public static final Item ITEM_Compressed_Carbon_Plate = null;

    //Ультимативная микросхема
    @GameRegistry.ObjectHolder("ultimate_circuit")
    public static final Item ITEM_Ultimate_Circuit = null;

    //Неифритовая катушка
    @GameRegistry.ObjectHolder("neifrit_coil")
    public static final Item ITEM_neifrit_coil = null;

    //Пустая оболочка
    @GameRegistry.ObjectHolder("emptyshell")
    public static final Item ITEM_empshel = null;

    //Преобразователь энергии
    @GameRegistry.ObjectHolder("powerconversion_module")
    public static final Item MODULE_power_convertion = null;

    //Нагревающий элемент
    @GameRegistry.ObjectHolder("heatingrod")
    public static final Item ITEM_hetrod = null;

    //Ядро ингибирования
    @GameRegistry.ObjectHolder("inhibition_core")
    public static final Item ITEM_The_inhibition = null;

    //Амулет
    @GameRegistry.ObjectHolder("amulet_erbi")
    public static final Item ITEM_erbi_amulet = null;

    @GameRegistry.ObjectHolder("spadiy_pickaxe")
    public static final Item TOOL_spadiy_pickaxe = null;

    @GameRegistry.ObjectHolder("spadiy_spade")
    public static final Item TOOL_spadiy_spade = null;

    @GameRegistry.ObjectHolder("spadiy_axe")
    public static final Item TOOL_spadiy_axe = null;

    @GameRegistry.ObjectHolder("spadiy_sword")
    public static final Item TOOL_spadiy_sword = null;

    /*
    Побочные продукты
    */

    //Побочный продук (0 уровня)
    @GameRegistry.ObjectHolder("second_product_0")
    public static final Item GENERATOR_sp_0 = null;

    //Побочный продук (1 уровня)
    @GameRegistry.ObjectHolder("second_product_1")
    public static final Item GENERATOR_sp_1 = null;

    //Побочный продук (2 уровня)
    @GameRegistry.ObjectHolder("second_product_2")
    public static final Item GENERATOR_sp_2 = null;

    //Побочный продук (3 уровня)
    @GameRegistry.ObjectHolder("second_product_3")
    public static final Item GENERATOR_sp_3 = null;

    /*
    Дальше идут охлаждалки
    */

    //Охлаждающий элемент (Уровень 1)
    @GameRegistry.ObjectHolder("heatsink_lvl.1")
    public static final Item  COMPONENT_1 = null;

    //Охлаждающий элемент (Уровень 2)
    @GameRegistry.ObjectHolder("heatsink_lvl.2")
    public static final Item  COMPONENT_2 = null;

    //Охлаждающий элемент (Уровень 3)
    @GameRegistry.ObjectHolder("heatsink_lvl.3")
    public static final Item  COMPONENT_3 = null;

    //Охлаждающий элемент (Уровень 4)
    @GameRegistry.ObjectHolder("heatsink_lvl.4")
    public static final Item  COMPONENT_4= null;

    //Охлаждающий элемент (Уровень 5)
    @GameRegistry.ObjectHolder("heatsink_lvl.5")
    public static final Item  COMPONENT_5= null;

    //Охлаждающий элемент (Уровень 6)
    @GameRegistry.ObjectHolder("heatsink_lvl.6")
    public static final Item  COMPONENT_6= null;

    //Охлаждающий элемент (Уровень 7)
    @GameRegistry.ObjectHolder("heatsink_lvl.7")
    public static final Item  COMPONENT_7= null;

    /*
    Улучшалки
    */

    //Улучшение объема
    @GameRegistry.ObjectHolder("upgrade_volecy")
    public static final Item UPGRADE_Volecy  = null;

    //Улучшение скорости
    @GameRegistry.ObjectHolder("upgrade_speed")
    public static final Item UPGRADE_speed  = null;

    //Улучшение для генератора
    @GameRegistry.ObjectHolder("upgrade_energy")
    public static final Item UPGRADE_energy  = null;

    //Улучшение для генератора
    @GameRegistry.ObjectHolder("upgrade_capacity")
    public static final Item UPGRADE_capacity  = null;

    //Улучшение для генератора
    @GameRegistry.ObjectHolder("upgrade_gift_energy")
    public static final Item UPGRADE_gift_energy  = null;

    //Улучшение для генератора
    @GameRegistry.ObjectHolder("upgrade_creative_energy")
    public static final Item UPGRADE_creative_energy  = null;

    /*
    Предметы
    */



    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> e) {
        //Item
        e.getRegistry().register(new Deterrent("deterrent", 1, TabsList.EXtabs));
        e.getRegistry().register(new DeterrentFilled("deterrent_filled", 1, TabsList.EXtabs));
        e.getRegistry().register(new ItemList("compressed_carbon_plate", 64));
        e.getRegistry().register(new ItemList("ultimate_circuit", 64));
        e.getRegistry().register(new ItemList("neifrit_coil", 64));
        e.getRegistry().register(new ItemList("emptyshell", 64));
        e.getRegistry().register(new ItemList("heatingrod", 64));
        e.getRegistry().register(new ItemList("inhibition_core", 64));
        e.getRegistry().register(new ErbiAmulet("amulet_erbi", 1, Configs.GeneralSettings.Item.amuletErbi_durability, TabsList.EXtabs));

        //Tools
        e.getRegistry().register(new Pickaxe(ModUtils.ToolMaterial_univ, "spadiy_pickaxe", TabsList.EXtabs));
        e.getRegistry().register(new Shovel(ModUtils.ToolMaterial_univ, "spadiy_spade", TabsList.EXtabs));
        e.getRegistry().register(new Axe(ModUtils.ToolMaterial_univ, "spadiy_axe", TabsList.EXtabs, 8.0F, 40.0F));
        e.getRegistry().register(new Sword(ModUtils.ToolMaterial_sword, "spadiy_sword", TabsList.EXtabs));

        //Ingot
        e.getRegistry().register(new ItemList("spadiy", 64));
        e.getRegistry().register(new ItemList("neifrit", 64));
        e.getRegistry().register(new ItemList("erbi", 64));

        //Plate
        e.getRegistry().register(new ItemList("spadiy_plate", 64));
        e.getRegistry().register(new ItemList("neifrit_plate", 64));
        e.getRegistry().register(new ItemList("neifrit_plate_dense", 64));
        e.getRegistry().register(new ItemList("spadiy_plate_dense", 64));

        //Casing
        e.getRegistry().register(new ItemList("neifrit_casing", 64));

        //Dust
        e.getRegistry().register(new ItemList("spadiy_dust", 64));
        e.getRegistry().register(new ItemList("neifrit_dust", 64));
        e.getRegistry().register(new ItemList("steel_dust", 64));

        //Module
        e.getRegistry().register(new ItemList("powerconversion_module", 1));

        //Upgrade
        e.getRegistry().register(new MachinesUpgradeSpeed("upgrade_speed", 16, TabsList.EXtabs));
        e.getRegistry().register(new MachinesUpgradeVolecy("upgrade_volecy", 16, TabsList.EXtabs));
        if(Configs.GeneralSettings.Upgrades.Energy.energy_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_energy", 1));

        if(Configs.GeneralSettings.Upgrades.Capacity.capacity_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_capacity", 1));

        if(Configs.GeneralSettings.Upgrades.GiftEnergy.giftEnergy_upgrade_bool)
            e.getRegistry().register(new ItemList("upgrade_gift_energy", 1));

        e.getRegistry().register(new ItemList("upgrade_creative_energy", 1));

        //Component
        e.getRegistry().register(new CoolingComp("heatsink_lvl.1", 1, Configs.GeneralSettings.Item.heatSink1_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.2", 1, Configs.GeneralSettings.Item.heatSink2_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.3", 1, Configs.GeneralSettings.Item.heatSink3_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.4", 1, Configs.GeneralSettings.Item.heatSink4_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.5", 1, Configs.GeneralSettings.Item.heatSink5_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.6", 1, Configs.GeneralSettings.Item.heatSink6_durability));
        e.getRegistry().register(new CoolingComp("heatsink_lvl.7", 1, Configs.GeneralSettings.Item.heatSink7_durability));

        //Second Products
        e.getRegistry().register(new SecondProducts_0("second_product_0", 64, TabsList.EXtabs));
        e.getRegistry().register(new SecondProducts_1("second_product_1", 64, TabsList.EXtabs));
        e.getRegistry().register(new ItemList("second_product_2", 64));
        e.getRegistry().register(new ItemList("second_product_3", 64));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegistryModel(ModelRegistryEvent e) {
        //Ingot
        assert false;
        registryModel(INGOT_spadiy);
        registryModel(INGOT_nefrit);
        registryModel(INGOT_erbi);

        //Plate
        registryModel(PLATE_spadiy);
        registryModel(PLATE_neifrit);
        registryModel(PLATE_neifrit_compres);
        registryModel(PLATE_spadiy_compres);

        //Casing
        registryModel(CASING_neifrit);

        //Dust
        registryModel(DUST_spadiy);
        registryModel(DUST_steel);
        registryModel(DUST_neifrit);

        //Item
        registryModel(ITEM_Compressed_Carbon_Plate);
        registryModel(ITEM_deterrent);
        registryModel(ITEM_deterrent_filled);
        registryModel(ITEM_Ultimate_Circuit);
        registryModel(ITEM_neifrit_coil);
        registryModel(ITEM_empshel);
        registryModel(ITEM_hetrod);
        registryModel(ITEM_The_inhibition);
        registryModel(ITEM_erbi_amulet);

        //Tools
        registryModel(TOOL_spadiy_pickaxe);
        registryModel(TOOL_spadiy_axe);
        registryModel(TOOL_spadiy_spade);
        registryModel(TOOL_spadiy_sword);

        //Module
        registryModel(MODULE_power_convertion);

        //Upgrade
        registryModel(UPGRADE_speed);
        registryModel(UPGRADE_Volecy);
        registryModel(UPGRADE_energy);
        registryModel(UPGRADE_capacity);
        registryModel(UPGRADE_gift_energy);
        registryModel(UPGRADE_creative_energy);

        //Component
        registryModel(COMPONENT_1);
        registryModel(COMPONENT_2);
        registryModel(COMPONENT_3);
        registryModel(COMPONENT_4);
        registryModel(COMPONENT_5);
        registryModel(COMPONENT_6);
        registryModel(COMPONENT_7);

        //Second production
        registryModel(GENERATOR_sp_0);
        registryModel(GENERATOR_sp_1);
        registryModel(GENERATOR_sp_2);
        registryModel(GENERATOR_sp_3);
    }

    // ВСЁ ЧТО НИЖЕ - НЕ ТРОГАЕМ
    @SideOnly(Side.CLIENT)
    private static void registryModel(Item item) {
        final ResourceLocation regName = item.getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        ModelBakery.registerItemVariants(item, mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }
}
