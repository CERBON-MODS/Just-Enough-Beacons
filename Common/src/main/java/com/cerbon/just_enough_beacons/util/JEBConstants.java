package com.cerbon.just_enough_beacons.util;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class JEBConstants {
    public static final String MOD_ID = "just_enough_beacons";
    public static final String MOD_NAME = "Just Enough Beacons";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceLocation BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/arrow.png");

    public static final String BETTER_BEACONS = "better_beacons";
    public static final String DOUBLE_SLABS = "doubleslabs";
}
