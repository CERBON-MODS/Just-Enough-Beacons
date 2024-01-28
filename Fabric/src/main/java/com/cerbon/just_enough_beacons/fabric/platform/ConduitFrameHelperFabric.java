package com.cerbon.just_enough_beacons.fabric.platform;

import com.cerbon.just_enough_beacons.platform.services.IConduitFrame;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

public class ConduitFrameHelperFabric implements IConduitFrame {
    public static Block[] conduitFrameBlocks = new Block[]{};

    @Override
    public boolean isConduitFrame(Block block) {
        return Arrays.stream(conduitFrameBlocks).anyMatch(block1 -> block1.defaultBlockState().is(block));
    }
}
