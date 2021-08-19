package me.domirusz24.pkmagicspells.config;

import com.nisovin.magicspells.Spell;
import com.sun.tools.sjavac.Log;
import me.domirusz24.pkmagicspells.PKMagicSpells;
import me.domirusz24.pkmagicspells.pk.PKSpell;
import me.domirusz24.pkmagicspells.pk.PKSpellAbility;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import me.domirusz24.plugincore.PluginCore;
import me.domirusz24.plugincore.util.CompleteListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public abstract class AbilityActivation implements CompleteListener {

    public static String getName(Class<? extends AbilityActivation> clazz) {
        if (clazz.isAnnotationPresent(AbilityActivationData.class)) {
            return clazz.getAnnotation(AbilityActivationData.class).value();
        } else {
            return null;
        }
    }

    public static void register(Class<? extends AbilityActivation> clazz) {
        String name = getName(clazz);
        ACTIVATION_BY_NAME.put(name, clazz);
        Bukkit.getLogger().log(Level.INFO, "|- Registered " + name + ".");
    }

    public static List<AbilityActivation> createInstances(SpellBender player, List<Class<? extends AbilityActivation>> activations) {
        List<AbilityActivation> instances = new ArrayList<>();
        for (Class<? extends AbilityActivation> activation : activations) {
            try {
                instances.add(activation.getDeclaredConstructor(SpellBender.class).newInstance(player));
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instances;
    }

    public static final HashMap<String, Class<? extends AbilityActivation>> ACTIVATION_BY_NAME = new HashMap<>();

    // --------------

    protected SpellBender player;

    public AbilityActivation(SpellBender player){
        this.player = player;
        registerListener();
    }

    public boolean isReady() {
        return ready;
    }

    private boolean ready = false;

    public boolean ready(boolean value) {
        ready = value;
        if (ready) {
            return player.tryCasting();
        }
        return false;
    }

    public void unregister() {
        unregisterListener();
        ready = false;
    }

    @Override
    public PluginCore getCorePlugin() {
        return PKMagicSpells.plugin;
    }
}
