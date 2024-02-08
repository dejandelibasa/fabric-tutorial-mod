package com.nodle66.tutorialmod.mixin;

import com.mojang.datafixers.util.Pair;
import com.nodle66.tutorialmod.block.ModBlocks;
import com.nodle66.tutorialmod.block.custom.SuperRailBlock;
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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({AbstractMinecartEntity.class})
public abstract class AbstractMinecartEntityMixin extends Entity {

    @Shadow protected abstract boolean willHitBlockAt(BlockPos pos);

    public AbstractMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    /*
    TODO: inject into protected void moveOnRail(BlockPos pos, BlockState state) { because onActivatorRail from tick is
     for the dismounting rails woopsie
     */
    @Invoker("willHitBlockAt")
    public abstract boolean invokeWillHitAtBlock(BlockPos pos);
    @Inject(method = "moveOnRail",
            at = @At(
                    value="TAIL"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void onSuperRail(BlockPos pos, BlockState state, CallbackInfo ci, double d, double e, double f, Vec3d vec3d, boolean bl, boolean bl2, double g, Vec3d vec3d2, RailShape railShape, Pair pair, Vec3i vec3i, Vec3i vec3i2, double h, double i, double j, double k, double l, Entity entity, double o, double p, double q, double r, double s, double t, double u, Vec3d vec3d4, int x, int y, Vec3d vec3d5, double w, Vec3d vec3d6, double aa, double ab) {
        AbstractMinecartEntity cast = (AbstractMinecartEntity) (Object) this;

        bl = false;
        if (state.isOf(ModBlocks.SUPER_RAIL)) {
            bl = state.get(SuperRailBlock.POWERED);
        }
        if (bl) {
            vec3d5 = cast.getVelocity();
            w = vec3d5.horizontalLength();
            if (w > 0.01) {
                cast.setVelocity(vec3d5.add(vec3d5.x / w * 0.06, 0.0, vec3d5.z / w * 0.06));
            } else {
                vec3d6 = cast.getVelocity();
                aa = vec3d6.x;
                ab = vec3d6.z;
                if (railShape == RailShape.EAST_WEST) {
                    if (((AbstractMinecartEntityMixin) cast).invokeWillHitAtBlock(pos.west())) {
                        aa = 0.02;
                    } else if (((AbstractMinecartEntityMixin) cast).invokeWillHitAtBlock(pos.east())) {
                        aa = -0.02;
                    }
                } else if (railShape == RailShape.NORTH_SOUTH) {
                    if (((AbstractMinecartEntityMixin) cast).invokeWillHitAtBlock(pos.north())) {
                        ab = 0.02;
                    } else if (((AbstractMinecartEntityMixin) cast).invokeWillHitAtBlock(pos.south())) {
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
