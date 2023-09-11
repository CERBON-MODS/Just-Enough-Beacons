package com.cerbon.just_enough_beacons.jei.conduitbaseblock;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ConduitFrameBlockRecipe(int index) {
    public static List<ItemStack> cache = new ArrayList<>();

    public List<ItemStack> getConduitFrameBlocksSublist(){
        return cache.subList(28 * index, Math.min(28 * index + 28, cache.size()));
    }

    public static void refresh() {
        cache.clear();
        cache = getConduitFrameBlocks();
    }

    private static List<ItemStack> getConduitFrameBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.isConduitFrame(block.defaultBlockState(), null, null, null))
                .map(ItemStack::new).collect(Collectors.toList());
    }
}
