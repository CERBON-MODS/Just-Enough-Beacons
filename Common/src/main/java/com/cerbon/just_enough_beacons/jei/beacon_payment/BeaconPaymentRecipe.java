package com.cerbon.just_enough_beacons.jei.beacon_payment;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BeaconPaymentRecipe(int index) {
    public static List<ItemStack> cache = new ArrayList<>();

    public List<ItemStack> getBeaconPaymentSublist(){
        return cache.subList(28 * index, Math.min(28 * index + 28, cache.size()));
    }

    public static void refresh() {
        cache.clear();
        cache = getBeaconPayments();
    }

    private static List<ItemStack> getBeaconPayments() {
        return BuiltInRegistries.ITEM.stream()
                .filter(item -> item.getDefaultInstance().is(ItemTags.BEACON_PAYMENT_ITEMS))
                .map(ItemStack::new).collect(Collectors.toList());
    }
}