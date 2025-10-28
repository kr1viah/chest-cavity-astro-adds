package net.astrospud.ccastroadds;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.astrospud.ccastroadds.config.CCAAConfig;
import net.astrospud.ccastroadds.listeners.CCAAOrganFoodEffectListeners;
import net.astrospud.ccastroadds.listeners.CCAAOrganOnDamageListeners;
import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.astrospud.ccastroadds.listeners.CCAAOrganTickListeners;
import net.astrospud.ccastroadds.registration.CCAADispenserBehaviors;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.registration.CCAAItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.config.CCConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCAstroAdds implements ModInitializer {
	public static CCAAConfig config;
	public static final String MOD_ID = "ccastroadds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

//	public static final ItemGroup ORGAN_ITEM_GROUP = FabricItemGroupBuilder.build(
//			new Identifier(MOD_ID, "organs"),
//			() -> new ItemStack(CCAAItems.STEEL_DISTRIBUTOR));

    public static final RegistryKey<ItemGroup> ORGAN_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "organ_items"));

	@Override
	public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, ORGAN_ITEM_GROUP, FabricItemGroup.builder().build());

		AutoConfig.register(CCAAConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(CCAAConfig.class).getConfig();

		ChestCavity.config = ChestCavity.config == null ? new CCConfig() : ChestCavity.config;

		CCAAItems.registerModItems();
		KeyBindings.register();
		CCAAOrganTickListeners.register();
		CCAAOrganOnDamageListeners.register();
		CCAADispenserBehaviors.register();
		CCAAOrganOnHitListeners.register();
		CCAAOrganFoodEffectListeners.register();
		CCAAStatusEffects.register();
	}
}
