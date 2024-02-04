package com.nodle66.tutorialmod.datagen;

import com.nodle66.tutorialmod.block.ModBlocks;
import com.nodle66.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.SOUND_BLOCK);
        addDrop(ModBlocks.RAW_RUBY_BLOCK, modOreDrops(ModBlocks.RAW_RUBY_BLOCK, ModItems.RAW_RUBY, UniformLootNumberProvider.create(2.0f, 5.0f)));
    }

    public LootTable.Builder modOreDrops(Block drop, Item item, UniformLootNumberProvider dropNumbers) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder) this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(
                                        dropNumbers))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))));
    }
}
