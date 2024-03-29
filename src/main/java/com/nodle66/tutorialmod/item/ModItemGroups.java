package com.nodle66.tutorialmod.item;

import com.nodle66.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import com.nodle66.tutorialmod.TutorialMod;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(Items.CRAFTING_TABLE);
                        entries.add(ModItems.METAL_DETECTOR);
                        entries.add(ModItems.URANIUM);
                        entries.add(ModItems.RUBY);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RUBY_BUTTON);
                        entries.add(ModBlocks.RUBY_SLAB);
                        entries.add(ModBlocks.RUBY_STAIRS);
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);
                        entries.add(Items.DIAMOND);
                        entries.add(Blocks.DIAMOND_BLOCK);
                        entries.add(ModBlocks.SOUND_BLOCK);
                        entries.add(ModItems.MEATBREAD);
                        entries.add(ModBlocks.SUPER_RAIL);
                    }).build());


    public static void registerItemGroups() {
        TutorialMod.Logger("Registering Item Groups for " + TutorialMod.MOD_ID);
    }
}
