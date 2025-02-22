/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2021 Skytils
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package skytils.skytilsmod.mixins.transformers.multiplayer;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import skytils.skytilsmod.mixins.hooks.multiplayer.PlayerControllerMPHookKt;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Inject(method = "isPlayerRightClickingOnEntity", at = @At("HEAD"), cancellable = true)
    private void onRightClickEntity(EntityPlayer player, Entity target, MovingObjectPosition movingObject, CallbackInfoReturnable<Boolean> cir) {
        PlayerControllerMPHookKt.handleRightClickEntity(player, target, movingObject, cir);
    }

    @Inject(method = "interactWithEntitySendPacket", at = @At("HEAD"), cancellable = true)
    private void onInteractWithEntitySendPacket(EntityPlayer player, Entity target, CallbackInfoReturnable<Boolean> cir) {
        PlayerControllerMPHookKt.handleRightClickEntity(player, target, null, cir);
    }

    @Inject(method = "onPlayerDamageBlock", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamageBlock(BlockPos pos, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        PlayerControllerMPHookKt.onPlayerDamageBlock(pos, directionFacing, cir);
    }
}
