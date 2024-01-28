package com.cerbon.just_enough_beacons.forge;

import com.cerbon.just_enough_beacons.JustEnoughBeacons;
import com.cerbon.just_enough_beacons.util.ModConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(ModConstants.MOD_ID)
public class JustEnoughBeaconsForge {

    public JustEnoughBeaconsForge() {
        JustEnoughBeacons.init();
    }
}