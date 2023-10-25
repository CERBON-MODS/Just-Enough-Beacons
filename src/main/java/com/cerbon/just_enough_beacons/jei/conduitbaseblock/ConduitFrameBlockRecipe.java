package com.cerbon.just_enough_beacons.jei.conduitbaseblock;

import cjminecraft.doubleslabs.common.blocks.DynamicSlabBlock;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import com.cerbon.just_enough_beacons.util.JEBUtils;
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
        if (JEBUtils.isModLoaded(JEBConstants.DOUBLE_SLABS))
            return ForgeRegistries.BLOCKS.getValues().stream()
                    .filter(block -> !(block instanceof DynamicSlabBlock) && block.isConduitFrame(block.defaultBlockState(), null, null, null))
                    .map(ItemStack::new).collect(Collectors.toList());

        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.isConduitFrame(block.defaultBlockState(), null, null, null))
                .map(ItemStack::new).collect(Collectors.toList());
    }
}
