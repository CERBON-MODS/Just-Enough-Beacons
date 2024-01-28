package com.cerbon.just_enough_beacons.forge.platform;

import com.cerbon.just_enough_beacons.platform.services.IConduitFrame;
import net.minecraft.world.level.block.Block;

public class ConduitFrameHelperForge implements IConduitFrame {

    @Override
    public boolean isConduitFrame(Block block) {
        return block.isConduitFrame(block.defaultBlockState(), null, null, null);
    }
}
