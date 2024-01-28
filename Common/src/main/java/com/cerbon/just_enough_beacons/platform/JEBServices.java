package com.cerbon.just_enough_beacons.platform;

import com.cerbon.just_enough_beacons.platform.services.IConduitFrame;
import com.cerbon.just_enough_beacons.util.JEBConstants;

import java.util.ServiceLoader;

public class JEBServices {
    public static final IConduitFrame PLATFORM_IS_CONDUIT_FRAME = load(IConduitFrame.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        JEBConstants.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
