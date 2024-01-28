package com.cerbon.just_enough_beacons.jei.conduit_base_block;

import com.cerbon.cerbons_api.api.static_utilities.MiscUtils;
import com.cerbon.just_enough_beacons.platform.JEBServices;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

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
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> {
                    if (MiscUtils.isModLoaded(JEBConstants.DOUBLE_SLABS)) {
                        try {
                            Class<?> dynamicSlabBlockClass = Class.forName("cjminecraft.doubleslabs.common.block.DynamicSlabBlock");
                            return !dynamicSlabBlockClass.isInstance(block) && JEBServices.PLATFORM_IS_CONDUIT_FRAME.isConduitFrame(block);

                        } catch (ClassNotFoundException e) {
                            JEBConstants.LOGGER.error("Couldn't find class DynamicSlabBlock", e);
                        }
                    }
                    return JEBServices.PLATFORM_IS_CONDUIT_FRAME.isConduitFrame(block);
                }).map(ItemStack::new).collect(Collectors.toList());
    }
}
