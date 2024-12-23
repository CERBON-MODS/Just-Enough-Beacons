package com.cerbon.just_enough_beacons.jei;

import com.cerbon.just_enough_beacons.jei.beacon_base_block.BeaconBaseBlockCategory;
import com.cerbon.just_enough_beacons.jei.beacon_base_block.BeaconBaseBlockRecipe;
import com.cerbon.just_enough_beacons.jei.beacon_payment.BeaconPaymentCategory;
import com.cerbon.just_enough_beacons.jei.beacon_payment.BeaconPaymentRecipe;
import com.cerbon.just_enough_beacons.jei.conduit_base_block.ConduitFrameBlockCategory;
import com.cerbon.just_enough_beacons.jei.conduit_base_block.ConduitFrameBlockRecipe;
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
        return ResourceLocation.fromNamespaceAndPath(JEBConstants.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new BeaconBaseBlockCategory(guiHelper), new BeaconPaymentCategory(guiHelper), new ConduitFrameBlockCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        BeaconBaseBlockRecipe.refresh();
        BeaconPaymentRecipe.refresh();
        ConduitFrameBlockRecipe.refresh();

        int beaconBaseBlockPages = (int) Math.ceil(BeaconBaseBlockRecipe.cache.size()/28D);
        int beaconPaymentPages = (int) Math.ceil(BeaconPaymentRecipe.cache.size()/28D);
        int conduitFrameBlockPages = (int) Math.ceil(ConduitFrameBlockRecipe.cache.size()/28D);

        List<BeaconBaseBlockRecipe> beaconBaseBlockRecipes = new ArrayList<>();
        List<BeaconPaymentRecipe> beaconPaymentRecipes = new ArrayList<>();
        List<ConduitFrameBlockRecipe> conduitFrameBlockRecipes = new ArrayList<>();

        IntStream.range(0, beaconBaseBlockPages).forEach(i -> beaconBaseBlockRecipes.add(new BeaconBaseBlockRecipe(i)));
        IntStream.range(0, beaconPaymentPages).forEach(i -> beaconPaymentRecipes.add(new BeaconPaymentRecipe(i)));
        IntStream.range(0, conduitFrameBlockPages).forEach(i -> conduitFrameBlockRecipes.add(new ConduitFrameBlockRecipe(i)));

        registration.addRecipes(JEBRecipeTypes.BEACON_BASE_BLOCK, beaconBaseBlockRecipes);
        registration.addRecipes(JEBRecipeTypes.BEACON_PAYMENT, beaconPaymentRecipes);
        registration.addRecipes(JEBRecipeTypes.CONDUIT_FRAME_BLOCK, conduitFrameBlockRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks.BEACON), JEBRecipeTypes.BEACON_BASE_BLOCK);
        registration.addRecipeCatalyst(new ItemStack(Blocks.BEACON), JEBRecipeTypes.BEACON_PAYMENT);
        registration.addRecipeCatalyst(new ItemStack(Blocks.CONDUIT), JEBRecipeTypes.CONDUIT_FRAME_BLOCK);
    }
}
