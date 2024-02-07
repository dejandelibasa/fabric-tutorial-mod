package com.nodle66.tutorialmod.mixin;

import com.nodle66.tutorialmod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({MinecartEntity.class})
public abstract class MinecartEntityMixin extends AbstractMinecartEntity {


    public MinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onActivatorRail(int x, int y, int z, boolean powered) {
        PlayerEntity player = getWorld().getClosestPlayer(x, y, z, 32, false);
        if(player != null) {
            player.sendMessage(Text.literal("Bă cfs"));
        }
        if (powered) {
            if (this.hasPassengers()) {
                this.removeAllPassengers();
            }
            BlockState blockState = this.getWorld().getBlockState(new BlockPos(x, y, z));
            if (this.getDamageWobbleTicks() == 0) {
                this.setDamageWobbleSide(-this.getDamageWobbleSide());
                this.setDamageWobbleTicks(10);
                this.setDamageWobbleStrength(50.0f);
                this.scheduleVelocityUpdate();
            }
        }
    }
}
