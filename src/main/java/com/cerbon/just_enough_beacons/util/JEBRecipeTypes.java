package com.cerbon.just_enough_beacons.util;

import com.cerbon.just_enough_beacons.jei.beaconbaseblock.BeaconBaseBlockRecipe;
import com.cerbon.just_enough_beacons.jei.beaconpayment.BeaconPaymentRecipe;
import com.cerbon.just_enough_beacons.jei.conduitbaseblock.ConduitFrameBlockRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JEBRecipeTypes {
    public static final RecipeType<BeaconBaseBlockRecipe> BEACON_BASE_BLOCK = RecipeType.create(JEBConstants.MOD_ID, "beacon_base_block", BeaconBaseBlockRecipe.class);
    public static final RecipeType<BeaconPaymentRecipe> BEACON_PAYMENT = RecipeType.create(JEBConstants.MOD_ID, "beacon_payment", BeaconPaymentRecipe.class);
    public static final RecipeType<ConduitFrameBlockRecipe> CONDUIT_FRAME_BLOCK = RecipeType.create(JEBConstants.MOD_ID, "conduit_frame_block", ConduitFrameBlockRecipe.class);
}
