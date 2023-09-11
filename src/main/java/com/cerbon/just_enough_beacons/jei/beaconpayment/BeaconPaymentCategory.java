package com.cerbon.just_enough_beacons.jei.beaconpayment;

import com.cerbon.just_enough_beacons.jei.conduitbaseblock.ConduitFrameBlockRecipe;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import com.cerbon.just_enough_beacons.util.JEBRecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class BeaconPaymentCategory implements IRecipeCategory<BeaconPaymentRecipe> {
    private final IGuiHelper guiHelper;

    public BeaconPaymentCategory(IGuiHelper guiHelper){
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return null;
    }

    @Override
    public Class<? extends BeaconPaymentRecipe> getRecipeClass() {
        return BeaconPaymentRecipe.class;
    }

    @Override
    public @NotNull RecipeType<BeaconPaymentRecipe> getRecipeType() {
        return JEBRecipeTypes.BEACON_PAYMENT;
    }

    @Override
    public @NotNull Component getTitle() {
        return new TranslatableComponent("category.beacon_payments");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return guiHelper.createDrawable(JEBConstants.BACKGROUND, 0, 0, 128, 128);
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.BEACON));
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull BeaconPaymentRecipe recipe, @NotNull IFocusGroup focuses) {
        int xPos = 2;
        int yPos = 53;
        int count = recipe.getBeaconPaymentSublist().size();

        for (int y = 0; y < Math.ceil(count/7d); y++) {
            for (int x = 0; x < 7; x++) {
                int index = 7 * y + x;
                if (index >= count)break;
                builder.addSlot(RecipeIngredientRole.CATALYST, xPos + 18 * x, yPos + 18 * y).addItemStack(recipe.getBeaconPaymentSublist().get(index));
            }
        }

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 55, 5).addItemStack(new ItemStack(Blocks.BEACON));
    }
}
