package net.astrospud.ccastroadds;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.astrospud.ccastroadds.config.CCAAConfig;
import net.astrospud.ccastroadds.listeners.CCAAOrganFoodEffectListeners;
import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.astrospud.ccastroadds.listeners.CCAAOrganTickListeners;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.registration.CCAAItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCAstroAdds implements ModInitializer {
	public static CCAAConfig config;
	public static final String MOD_ID = "ccastroadds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AutoConfig.register(CCAAConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(CCAAConfig.class).getConfig();

		CCAAItems.registerModItems();
		CCAAOrganTickListeners.register();
		CCAAOrganOnHitListeners.register();
		CCAAOrganFoodEffectListeners.register();
		CCAAStatusEffects.register();
	}
}
