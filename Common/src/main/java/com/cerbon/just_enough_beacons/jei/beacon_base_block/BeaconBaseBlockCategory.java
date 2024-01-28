package com.cerbon.just_enough_beacons.jei.beacon_base_block;

import com.cerbon.cerbons_api.api.static_utilities.MiscUtils;
import com.cerbon.cerbons_api.api.static_utilities.RegistryUtils;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import com.cerbon.just_enough_beacons.util.JEBRecipeTypes;
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

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

                builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 55, 5).addItemStack(new ItemStack(Blocks.BEACON));

                if (MiscUtils.isModLoaded(JEBConstants.BETTER_BEACONS)) {
                    try {
                        Class<?> bbConfigClass = Class.forName("com.cerbon.better_beacons.config.BBConfig");
                        Class<?> rangeAndAmplifierClass = Class.forName("com.cerbon.better_beacons.config.custom.RangeAndAmplifier");
                        Class<?> baseBlocksAmplifierManagerClass = Class.forName("com.cerbon.better_beacons.datapack.BaseBlocksAmplifierManager");

                        Field beaconRangeAndAmplifierField = bbConfigClass.getField("beaconRangeAndAmplifier");
                        Field isBaseBlockAmplifierEnabledField = rangeAndAmplifierClass.getField("isBaseBlockAmplifierEnabled");
                        Method getBlockAmplifierMapMethod = baseBlocksAmplifierManagerClass.getMethod("getBlockAmplifierMap");

                        Object bbConfigInstance = bbConfigClass.newInstance();
                        Object rangeAndAmplifierInstance = beaconRangeAndAmplifierField.get(bbConfigInstance);

                        boolean isBaseBlockAmplifierEnabled = (boolean) isBaseBlockAmplifierEnabledField.get(rangeAndAmplifierInstance);

                        if (!recipe.getBeaconBaseBlocksSublist().isEmpty() && isBaseBlockAmplifierEnabled) {
                            HashMap<Block, Integer> blockAmplifierMap = (HashMap<Block, Integer>) getBlockAmplifierMapMethod.invoke(null);
                            String itemId = RegistryUtils.getItemKeyAsString(itemStack.getItem());
                            Block block = RegistryUtils.getBlockByKey(itemId);
                            int amplifier = blockAmplifierMap.getOrDefault(block, 0);

                            slot.addTooltipCallback(((recipeSlotView, tooltip) -> tooltip.add(Component.translatable("jei.just_enough_beacons.base_blocks.tooltip", amplifier).withStyle(ChatFormatting.YELLOW))));
                        }
                    } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                        JEBConstants.LOGGER.error("Couldn't set beacon base blocks amplifier values.", e);
                    }
                }
            }
        }
    }

}
