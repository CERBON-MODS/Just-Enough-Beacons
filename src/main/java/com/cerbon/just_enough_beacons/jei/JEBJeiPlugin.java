package com.cerbon.just_enough_beacons.jei;

import com.cerbon.just_enough_beacons.util.JEBConstants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEBJeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(JEBConstants.MOD_ID, "jei_plugin");
    }
}
