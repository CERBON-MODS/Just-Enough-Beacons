package com.cerbon.just_enough_beacons.jei.beaconbaseblock;

import com.cerbon.better_beacons.config.BBCommonConfigs;
import com.cerbon.better_beacons.util.BBUtils;
import com.cerbon.better_beacons.util.json.BeaconBaseBlocksAmplifierManager;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import com.cerbon.just_enough_beacons.util.JEBRecipeTypes;
import com.cerbon.just_enough_beacons.util.JEBUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class BeaconBaseBlockCategory implements IRecipeCategory<BeaconBaseBlockRecipe> {

    private final IGuiHelper guiHelper;

    public BeaconBaseBlockCategory(IGuiHelper guiHelper){
        this.guiHelper = guiHelper;
    }

    @Override
    public @NotNull RecipeType<BeaconBaseBlockRecipe> getRecipeType() {
        return JEBRecipeTypes.BEACON_BASE_BLOCK;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("category.beacon_base_blocks");
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
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, BeaconBaseBlockRecipe recipe, @NotNull IFocusGroup focuses) {
        int xPos = 2;
        int yPos = 53;
        int count = recipe.getBeaconBaseBlocksSublist().size();

        for (int y = 0; y < Math.ceil(count/7d); y++) {
            for (int x = 0; x < 7; x++) {
                int index = 7 * y + x;
                if (index >= count)break;

                ItemStack itemStack = recipe.getBeaconBaseBlocksSublist().get(index);
                IRecipeSlotBuilder slot =  builder.addSlot(RecipeIngredientRole.CATALYST, xPos + 18 * x, yPos + 18 * y).addItemStack(itemStack);

                if (JEBUtils.isModLoaded(JEBConstants.BETTER_BEACONS)){
                    if (!recipe.getBeaconBaseBlocksSublist().isEmpty() && BBCommonConfigs.ENABLE_BASE_BLOCK_AMPLIFIER.get()){
                        String itemId = BBUtils.getItemKeyAsString(itemStack.getItem());
                        Block block = JEBUtils.getBlockByKey(itemId);
                        int amplifier = BeaconBaseBlocksAmplifierManager.getBlockAmplifierMap().getOrDefault(block, 0);

                        slot.addTooltipCallback(((recipeSlotView, tooltip) -> tooltip.add(Component.translatable("jei.just_enough_beacons.base_blocks.tooltip", amplifier).withStyle(ChatFormatting.YELLOW))));
                    }
                }
            }
        }

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 55, 5).addItemStack(new ItemStack(Blocks.BEACON));
    }

}
