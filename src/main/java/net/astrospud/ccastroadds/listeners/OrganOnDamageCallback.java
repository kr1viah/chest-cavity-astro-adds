package net.astrospud.ccastroadds.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganOnDamageCallback {
    Event<OrganOnDamageCallback> EVENT = EventFactory.createArrayBacked(OrganOnDamageCallback.class,
            (listeners) -> (entity, source, amount) -> {
                for (OrganOnDamageCallback listener : listeners) {
                    listener.onDamage(entity, source, amount);
                }
            });

    void onDamage(LivingEntity entity, DamageSource source, float amount);
}
