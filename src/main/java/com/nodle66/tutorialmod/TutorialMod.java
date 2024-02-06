package com.nodle66.tutorialmod;

import com.nodle66.tutorialmod.block.ModBlocks;
import com.nodle66.tutorialmod.item.ModItemGroups;
import com.nodle66.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.vehicle.MinecartEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;

public class TutorialMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tutorialmod";
	public static final String MOD_AUTHOR = "dejan.delibasa";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void Logger(String info) {
		TutorialMod.LOGGER.info(info);
	}

	@Override
	public void onInitialize() {
		TutorialMod.Logger(TutorialMod.MOD_ID + " by " + TutorialMod.MOD_AUTHOR + " loading!");

		// registering Items and ItemGroups
		ModItemGroups.registerItemGroups();
		ModItems.RegisterModItems();

		// registering Blocks
		ModBlocks.registerModBlocks();
    }
}