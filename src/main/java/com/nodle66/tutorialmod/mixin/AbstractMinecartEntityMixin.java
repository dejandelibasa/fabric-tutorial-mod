package com.nodle66.tutorialmod.mixin;

import com.nodle66.tutorialmod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({AbstractMinecartEntity.class})
public abstract class AbstractMinecartEntityMixin extends Entity {
    @Shadow public abstract void onActivatorRail(int x, int y, int z, boolean powered);

    public AbstractMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;moveOnRail(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V",
                    shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void onSuperRail(CallbackInfo ci, int i, int j, int k, BlockState blockState, BlockPos blockPos) {
        AbstractMinecartEntity cast = (AbstractMinecartEntity) (Object) this;
        if(blockState.isOf(ModBlocks.SUPER_RAIL)) {
            cast.onActivatorRail(i, j, k, blockState.get(PoweredRailBlock.POWERED));
        }
    }
}
