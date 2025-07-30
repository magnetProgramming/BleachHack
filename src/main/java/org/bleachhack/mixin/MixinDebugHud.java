package org.bleachhack.mixin;

import java.util.ArrayList;
import java.util.List;

import org.bleachhack.BleachHack;
import org.bleachhack.event.events.EventDebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.hud.DebugHud;

@Mixin(DebugHud.class)
public class MixinDebugHud {

    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    private void onGetLeftText(CallbackInfoReturnable<List<String>> cir) {
        List<String> lines = new ArrayList<>(cir.getReturnValue());

        cir.setReturnValue(lines); 
        
        try 
        {
        	BleachHack.eventBus.post(new EventDebugHud(lines));
        } catch (Exception ex) 
        {
        	System.out.println(ex.toString());
        }
    }
}

