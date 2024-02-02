package com.nodle66.tutorialmod;

import com.nodle66.tutorialmod.block.ModBlocks;
import com.nodle66.tutorialmod.item.ModItemGroups;
import com.nodle66.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void Logger(String info) {
		TutorialMod.LOGGER.info(info);
	}

	@Override
	public void onInitialize() {
		TutorialMod.Logger(TutorialMod.MOD_ID + "loading!");

		// registering Items and ItemGroups
		ModItemGroups.registerItemGroups();
		ModItems.RegisterModItems();

		// registering Blocks
		ModBlocks.registerModBlocks();
    }
}