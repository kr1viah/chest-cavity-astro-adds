package net.astrospud.ccastroadds.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.astrospud.ccastroadds.CCAstroAdds;

@Config(name = CCAstroAdds.MOD_ID)
public class CCAAConfig implements ConfigData {
    @ConfigEntry.Category("cooldown")
    public int REGROWTH_COOLDOWN = 600; //how often an entity regrows their organs when using 1 stem cell canister, in ticks
}
