package net.astrospud.ccastroadds;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding FLIGHT;


    public static void register(){
        FLIGHT = register(new Identifier("ccastroadds", "flight"), "util", GLFW.GLFW_KEY_PERIOD);
    }

    public static KeyBinding register(Identifier id,String category,int defaultKey){
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key."+id.getNamespace()+"."+id.getPath(), // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                defaultKey, // The keycode of the key
                "category."+id.getNamespace()+"."+category // The translation key of the keybinding's category.
        ));
    }
}
