package com.nodle66.tutorialmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import com.nodle66.tutorialmod.block.ModBlocks;
import com.nodle66.tutorialmod.block.custom.SuperRailBlock;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({AbstractMinecartEntity.class})
public abstract class AbstractMinecartEntityMixin extends Entity {
    public AbstractMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    /*
    TODO: inject into protected void moveOnRail(BlockPos pos, BlockState state) { because onActivatorRail from tick is
     for the dismounting rails woopsie
     */
    @Inject(method = "moveOnRail",
            at = @At(
                    value="TAIL"
            ))
    private void onSuperRail(CallbackInfo ci, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) BlockPos pos) {
        AbstractMinecartEntity cast = (AbstractMinecartEntity) (Object) this;
        RailShape railShape = state.get(((AbstractRailBlock)state.getBlock()).getShapeProperty());
        boolean bl = false;
        if (state.isOf(ModBlocks.SUPER_RAIL)) {
            bl = state.get(SuperRailBlock.POWERED);
        }
        if (bl) {
            Vec3d vec3d5 = cast.getVelocity();
            double w = vec3d5.horizontalLength();
            if (w > 0.01) {
                cast.setVelocity(vec3d5.add(vec3d5.x / w * 0.5, 0.0, vec3d5.z / w * 0.5));
            } else {
                Vec3d vec3d6 = cast.getVelocity();
                double aa = vec3d6.x;
                double ab = vec3d6.z;
                if (railShape == RailShape.EAST_WEST) {
                    if (((AbstractMinecartEntityInvoker) cast).invokeWillHitBlockAt(pos.west())) {
                        aa = 0.02;
                    } else if (((AbstractMinecartEntityInvoker) cast).invokeWillHitBlockAt(pos.east())) {
                        aa = -0.02;
                    }
                } else if (railShape == RailShape.NORTH_SOUTH) {
                    if (((AbstractMinecartEntityInvoker) cast).invokeWillHitBlockAt(pos.north())) {
                        ab = 0.02;
                    } else if (((AbstractMinecartEntityInvoker) cast).invokeWillHitBlockAt(pos.south())) {
                        ab = -0.02;
                    }
                } else {
                    return;
                }
                cast.setVelocity(aa*2, vec3d6.y, ab*2);
            }
        }
    }
}
