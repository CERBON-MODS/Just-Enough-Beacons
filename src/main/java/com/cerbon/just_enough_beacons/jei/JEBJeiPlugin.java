package com.cerbon.just_enough_beacons.jei;

import com.cerbon.just_enough_beacons.jei.beaconbaseblock.BeaconBaseBlockCategory;
import com.cerbon.just_enough_beacons.jei.beaconbaseblock.BeaconBaseBlockRecipe;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import com.cerbon.just_enough_beacons.util.JEBRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@JeiPlugin
public class JEBJeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(JEBConstants.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new BeaconBaseBlockCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        BeaconBaseBlockRecipe.refresh();
        int beaconBaseBlockPages = (int)Math.ceil(BeaconBaseBlockRecipe.cache.size()/28D);
        List<BeaconBaseBlockRecipe> beaconBaseBlockRecipes = new ArrayList<>();
        IntStream.range(0, beaconBaseBlockPages).forEach(i -> beaconBaseBlockRecipes.add(new BeaconBaseBlockRecipe(i)));
        registration.addRecipes(JEBRecipeTypes.BEACON_BASE_BLOCK, beaconBaseBlockRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks.BEACON), JEBRecipeTypes.BEACON_BASE_BLOCK);
    }
}
