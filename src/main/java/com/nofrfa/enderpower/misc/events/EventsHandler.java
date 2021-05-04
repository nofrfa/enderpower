package com.nofrfa.enderpower.misc.events;

import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EventsHandler {
    @SubscribeEvent
    public void tntDetonated(ExplosionEvent.Detonate event) {
        World world = event.getWorld();
        if(!world.isRemote) {
            for(Object entity : event.getAffectedEntities()) {
                if(entity instanceof EntityItem) {
                    if(((EntityItem) entity).getItem().isItemEqual(new ItemStack(ItemsRegistry.DUST_spadiy))) {
                        int newCount = getRandomNumber(((EntityItem) entity).getItem().getCount());
                        EntityItem entityToSpawn = new EntityItem(world, ((EntityItem) entity).getPosition().getX(), ((EntityItem) entity).getPosition().getY(), ((EntityItem) entity).getPosition().getZ(), new ItemStack(ItemsRegistry.INGOT_spadiy, newCount));
                        entityToSpawn.setGlowing(true);
                        world.spawnEntity(entityToSpawn);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onTarget(LivingSetAttackTargetEvent event) {
        if(event.getEntityLiving() instanceof EntityEnderman){
            EntityLiving entity = (EntityLiving) event.getEntityLiving();
            if(event.getTarget() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getTarget();
                NonNullList<ItemStack> invPlayer = player.inventory.mainInventory;

                for(ItemStack item2 : invPlayer) {
                    if(item2.isItemEqual(new ItemStack(ItemsRegistry.ITEM_erbi_amulet)))
                        entity.setAttackTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public void shulkerdetecter(LivingAttackEvent event) {
       Entity entity = event.getEntity();
       if(entity instanceof EntityPlayer) {
           if(event.getSource().getTrueSource() instanceof EntityShulker) {
               NonNullList<ItemStack> invPlayer = ((EntityPlayer) entity).inventory.mainInventory;
               for (ItemStack item : invPlayer) {
                   if (item.isItemEqualIgnoreDurability(new ItemStack(ItemsRegistry.ITEM_erbi_amulet))) {
                       event.setCanceled(true);
                       item.damageItem(1, event.getEntityLiving());
                       for (ItemStack item2 : invPlayer) {
                           if (item2.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent))) {
                               item2.setCount(0);
                               NBTTagCompound nbttt = new NBTTagCompound();
                               nbttt.setString("inside", "shulker_projectile");
                               ItemStack itemStack = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);
                               itemStack.setTagCompound(nbttt);
                               ((EntityPlayer) entity).inventory.addItemStackToInventory(itemStack);
                               item.damageItem(1, event.getEntityLiving());
                               break;
                           }
                       }
                       break;
                   }
               }
           }
       }
    }

    @SubscribeEvent
    public void playerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        World world = event.getWorld();
        if(!world.isRemote) {
            Entity entity = event.getTarget();
            EnumHand handIs = event.getHand();
            ItemStack itemIs = event.getItemStack();
            if (entity instanceof EntityShulkerBullet) {
                if (itemIs.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent)) && handIs == EnumHand.MAIN_HAND) {
                    EntityPlayer player = event.getEntityPlayer();
                    if (player.dimension != 1) {
                        player.sendMessage(new TextComponentString(I18n.format("chatinfo.tag") + I18n.format("event.fail")));
                    } else {
                        NBTTagCompound inside = new NBTTagCompound();
                        inside.setString("inside", "shulker_projectile");

                        entity.setDead();
                        itemIs.setCount(itemIs.getCount() - 1);

                        ItemStack finalItem = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);
                        finalItem.setTagCompound(inside);

                        player.inventory.addItemStackToInventory(finalItem);
                    }
                }
            }
        }
    }

    private int getRandomNumber(int max_1) {
        if(max_1 >= 4) {
            return new Random().nextInt(max_1);
        } else return max_1;
    }
}