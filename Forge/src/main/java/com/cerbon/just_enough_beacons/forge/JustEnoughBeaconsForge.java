package com.cerbon.just_enough_beacons.forge;

import com.cerbon.just_enough_beacons.JustEnoughBeacons;
import com.cerbon.just_enough_beacons.util.JEBConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(JEBConstants.MOD_ID)
public class JustEnoughBeaconsForge {

    public JustEnoughBeaconsForge() {
        JustEnoughBeacons.init();
    }
}