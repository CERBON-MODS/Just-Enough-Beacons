package com.cerbon.just_enough_beacons.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class JEBUtils {
    public static boolean isModLoaded(String modid){
        return ModList.get().isLoaded(modid);
    }

    public static Block getBlockByKey(String key){
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(key));
    }

    public static String getItemKeyAsString(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).toString();
    }
}
