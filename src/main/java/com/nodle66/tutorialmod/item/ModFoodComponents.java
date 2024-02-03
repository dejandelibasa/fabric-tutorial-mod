package com.nodle66.tutorialmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent MEATBREAD = new FoodComponent.Builder()
            .hunger(12)
            .saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(
                    StatusEffects.REGENERATION,
                    100), 0.75f)
            .build();
}
