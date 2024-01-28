package com.cerbon.just_enough_beacons.fabric.mixin.block_entity;

import com.cerbon.just_enough_beacons.fabric.platform.ConduitFrameHelperFabric;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConduitBlockEntity.class)
public abstract class ConduitBlockEntityMixin {
    @Shadow @Final private static Block[] VALID_BLOCKS;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void jeb_getConduitFrameBlocks(BlockPos pos, BlockState blockState, CallbackInfo ci) {
        ConduitFrameHelperFabric.conduitFrameBlocks = VALID_BLOCKS;
    }
}
