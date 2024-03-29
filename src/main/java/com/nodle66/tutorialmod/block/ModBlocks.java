package com.nodle66.tutorialmod.block;

import com.nodle66.tutorialmod.TutorialMod;
import com.nodle66.tutorialmod.block.custom.SoundBlock;
import com.nodle66.tutorialmod.block.custom.SuperRailBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block RUBY_BLOCK = registerBlock(
            "ruby_block",
            new Block(FabricBlockSettings.create().
                    mapColor(MapColor.RED).
                    instrument(Instrument.BASEDRUM).
                    requiresTool().
                    strength(5.0F, 6.0F).
                    sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).mapColor(MapColor.RED).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block SOUND_BLOCK = registerBlock("sound_block", new SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RUBY_STAIRS = registerBlock("ruby_stairs", new StairsBlock(ModBlocks.RUBY_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RUBY_SLAB = registerBlock("ruby_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RUBY_BUTTON = registerBlock("ruby_button", new ButtonBlock(BlockSetType.IRON, 10, FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block SUPER_RAIL = registerBlock("super_rail", new SuperRailBlock(FabricBlockSettings.copyOf(Blocks.POWERED_RAIL)));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TutorialMod.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        TutorialMod.Logger("Registering blocks for " + TutorialMod.MOD_ID);
    }
}
