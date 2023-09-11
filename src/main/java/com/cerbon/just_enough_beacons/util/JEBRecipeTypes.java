package com.cerbon.just_enough_beacons.util;

import com.cerbon.just_enough_beacons.jei.beaconbaseblock.BeaconBaseBlockRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JEBRecipeTypes {
    public static final RecipeType<BeaconBaseBlockRecipe> BEACON_BASE_BLOCK = RecipeType.create(JEBConstants.MOD_ID, "beacon_block_recipe", BeaconBaseBlockRecipe.class);
}
