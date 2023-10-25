package com.cerbon.just_enough_beacons.util;

import net.minecraftforge.fml.ModList;

public class JEBUtils {
    public static boolean isModLoaded(String modid){
        return ModList.get().isLoaded(modid);
    }
}
