package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;


@Mixin(ChestCavityInstance.class)
public abstract class ChestCavityInstanceMixin{
    @Inject(at = @At("RETURN"), method = "getOrganScores", cancellable = true)
    public void ccaagetOrganScoresMixin(CallbackInfoReturnable<Map<Identifier,Float>> cir) {
        Map<Identifier,Float> organScores = cir.getReturnValue();
        Map<Identifier,Float> organScores1 = new HashMap<>();
        Identifier id = new Identifier(CCAstroAdds.MOD_ID, CCAAOrganScores.SOLAR_PLEXUS.getPath());
        float solar_plexus = organScores.getOrDefault(id, 0f);
        if (solar_plexus != 0) {
            organScores.forEach((id1, f) -> organScores1.put(id1, f * (solar_plexus+1)));
            organScores1.get(id);
            organScores1.replace(id, solar_plexus);
            cir.setReturnValue(organScores1);
        }
    }

    @Inject(at = @At("RETURN"), method = "getOrganScore", cancellable = true)
    public void ccaagetOrganScoreMixin(Identifier id, CallbackInfoReturnable<Float> cir) {
        float organScore = cir.getReturnValue();
        Identifier id1 = new Identifier(CCAstroAdds.MOD_ID, CCAAOrganScores.SOLAR_PLEXUS.getPath());
        float solar_plexus = ((ChestCavityInstance)(Object)this).getOrganScore(id1);
        if (solar_plexus != 0) {
            cir.setReturnValue(organScore * (solar_plexus+1));
        }
    }
}
