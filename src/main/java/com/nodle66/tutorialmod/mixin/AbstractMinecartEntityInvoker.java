package com.nodle66.tutorialmod.mixin;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({AbstractMinecartEntity.class})
public interface AbstractMinecartEntityInvoker {
    @Invoker("willHitBlockAt")
    public  boolean invokeWillHitBlockAt(BlockPos pos);
}
