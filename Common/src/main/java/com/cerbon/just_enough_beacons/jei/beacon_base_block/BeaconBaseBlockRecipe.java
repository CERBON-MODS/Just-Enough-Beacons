package com.cerbon.just_enough_beacons.jei.beacon_base_block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BeaconBaseBlockRecipe(int index) {
    public static List<ItemStack> cache = new ArrayList<>();

    public List<ItemStack> getBeaconBaseBlocksSublist() {
        return cache.subList(28 * index, Math.min(28 * index + 28, cache.size()));
    }

    public static void refresh() {
        cache.clear();
        cache = getBeaconBaseBlocks();
    }

    private static List<ItemStack> getBeaconBaseBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> block.defaultBlockState().is(BlockTags.BEACON_BASE_BLOCKS))
                .map(ItemStack::new).collect(Collectors.toList());
    }
}
