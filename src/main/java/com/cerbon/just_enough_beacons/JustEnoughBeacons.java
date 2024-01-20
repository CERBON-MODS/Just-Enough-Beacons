package com.cerbon.just_enough_beacons;

import com.cerbon.just_enough_beacons.util.JEBConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JEBConstants.MOD_ID)
public class JustEnoughBeacons {

    public JustEnoughBeacons() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
