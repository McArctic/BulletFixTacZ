package org.mcarctic.bulletfixtacz.mixin;

import com.tacz.guns.util.block.BlockRayTrace;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.valkyrienskies.mod.common.world.RaycastUtilsKt;

import java.util.function.Predicate;

@Mixin(
        value = {BlockRayTrace.class},
        remap = false
)
public abstract class MixinBlockRayTrace {
    @Inject(
            method = {"rayTraceBlocks"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private static void injectRayTraceBlocks(Level level, ClipContext context, CallbackInfoReturnable<BlockHitResult> cir) {
        cir.setReturnValue(RaycastUtilsKt.clipIncludeShips(level, context));
    }
}
