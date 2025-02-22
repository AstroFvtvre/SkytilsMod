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

package skytils.skytilsmod.mixins.transformers.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import skytils.skytilsmod.Skytils;
import skytils.skytilsmod.events.SetActionBarEvent;
import skytils.skytilsmod.mixins.hooks.gui.GuiIngameHookKt;
import skytils.skytilsmod.utils.RenderUtil;
import skytils.skytilsmod.utils.Utils;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends Gui {

    @Shadow protected String recordPlaying;

    @Shadow protected int recordPlayingUpFor;

    @Shadow protected boolean recordIsPlaying;

    @Inject(method = "setRecordPlaying(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    private void onSetActionBar(String message, boolean isPlaying, CallbackInfo ci) {
        if (GuiIngameHookKt.onSetActionBar(message, isPlaying, ci)) {
            this.recordPlaying = GuiIngameHookKt.getRecordPlaying();
            this.recordPlayingUpFor = GuiIngameHookKt.getRecordPlayingUpFor();
            this.recordIsPlaying = GuiIngameHookKt.getRecordIsPlaying();
        }
    }

    @Inject(method = "renderHotbarItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderItemAndEffectIntoGUI(Lnet/minecraft/item/ItemStack;II)V"))
    private void renderRarityOnHotbar(int index, int xPos, int yPos, float partialTicks, EntityPlayer player, CallbackInfo ci) {
        GuiIngameHookKt.renderRarityOnHotbar(index, xPos, yPos, partialTicks, player, ci);
    }

}
