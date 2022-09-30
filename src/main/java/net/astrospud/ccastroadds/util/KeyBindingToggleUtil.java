package net.astrospud.ccastroadds.util;

import net.astrospud.ccastroadds.KeyBindings;
import net.tigereye.chestcavity.registration.CCKeybindings;

public class KeyBindingToggleUtil {
    private static boolean flightButton = false;
    public static boolean isFlying = false;

    public static void tick() {
        if ((KeyBindings.FLIGHT.isPressed() || CCKeybindings.UTILITY_ABILITIES.isPressed()) && !flightButton) {
            flightButton = true;
            isFlying = !isFlying;
        }
        if (!KeyBindings.FLIGHT.isPressed() && !CCKeybindings.UTILITY_ABILITIES.isPressed()) {
            flightButton = false;
        }
    }
}
