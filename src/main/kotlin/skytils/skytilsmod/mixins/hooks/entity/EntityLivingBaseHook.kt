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
package skytils.skytilsmod.mixins.hooks.entity

import skytils.skytilsmod.Skytils
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import net.minecraft.world.World
import net.minecraft.util.EnumParticleTypes
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.Entity
import net.minecraft.potion.Potion
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.*
import skytils.skytilsmod.utils.*

fun modifyPotionActive(potion: Potion, cir: CallbackInfoReturnable<Boolean>) {
    if (!Utils.inSkyblock) return
    if (Skytils.config.disableNightVision && potion === Potion.nightVision) {
        cir.returnValue = false
    }
}

fun modifyPotionActive(potionId: Int, cir: CallbackInfoReturnable<Boolean>) {
    if (!Utils.inSkyblock) return
    if (Skytils.config.disableNightVision && potionId == Potion.nightVision.id) {
        cir.returnValue = false
    }
}

fun removeDeathParticle(
    world: World,
    particleType: EnumParticleTypes,
    xCoord: Double,
    yCoord: Double,
    zCoord: Double,
    xOffset: Double,
    yOffset: Double,
    zOffset: Double,
    p_175688_14_: IntArray
) {
    if (!(Skytils.config.hideDeathParticles && particleType == EnumParticleTypes.EXPLOSION_NORMAL)) {
        world.spawnParticle(particleType, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, *p_175688_14_)
    }
}
