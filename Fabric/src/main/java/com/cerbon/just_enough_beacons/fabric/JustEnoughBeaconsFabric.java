package com.cerbon.just_enough_beacons.fabric;

import com.cerbon.just_enough_beacons.JustEnoughBeacons;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class JustEnoughBeaconsFabric implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        JustEnoughBeacons.init();
    }

    @Override
    public void onInitializeClient() {}
}