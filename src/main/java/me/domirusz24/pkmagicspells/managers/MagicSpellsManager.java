package me.domirusz24.pkmagicspells.managers;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.CoreAbility;
import me.domirusz24.pkmagicspells.PKMagicSpells;
import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.activations.RightClickActivation;
import me.domirusz24.pkmagicspells.activations.ShiftActivation;
import me.domirusz24.pkmagicspells.activations.element.*;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.MagicSpellsConfig;
import me.domirusz24.pkmagicspells.pk.PKSpell;
import me.domirusz24.pkmagicspells.pk.PKSpellAbility;
import me.domirusz24.pkmagicspells.pk.PKSpellAbilityExtend;
import me.domirusz24.plugincore.PluginCore;
import me.domirusz24.plugincore.managers.ConfigManager;
import me.domirusz24.plugincore.managers.Manager;
import net.bytebuddy.ByteBuddy;
import org.bukkit.Bukkit;
import org.bukkit.Warning;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static me.domirusz24.pkmagicspells.config.AbilityActivation.register;

public class MagicSpellsManager extends Manager implements ConfigManager.Reloadable {

    private final MagicSpells magicSpells;

    public MagicSpellsManager(PluginCore plugin, MagicSpells magicSpells) {
        super(plugin);
        Bukkit.getLogger().log(Level.WARNING, " §4§l--- §r§cLOADING PKSpellAbilities, THIS MAY TAKE A WHILE §4§l---");
        this.magicSpells = magicSpells;
        defaultActivators();
    }

    private void defaultActivators() {
        register(LeftClickActivation.class);
        register(RightClickActivation.class);
        register(ShiftActivation.class);

        register(EarthActivationNear.class);
        register(EarthActivationTarget.class);

        register(SandActivationTarget.class);

        register(WaterActivationNear.class);
        register(WaterActivationTarget.class);

        register(PlantActivationTarget.class);

        register(MetalActivationTarget.class);

        register(IceActivationNear.class);
        register(IceActivationTarget.class);

        register(LavaActivationNear.class);
        register(LavaActivationTarget.class);
    }

    public void load() {

        PKMagicSpells.magicSpellsConfig.reload();

        for (Spell spell : MagicSpells.spells()) {
            if (spell.isHelperSpell()) continue;
            if (!PKSpell.SPELL_BY_NAME.containsKey(spell.getName())) {
                new PKSpell(spell);
            }
        }
        List<String> allNames = MagicSpells.spells().stream().map(Spell::getInternalName).collect(Collectors.toList());
        for (String s : new ArrayList<>(PKSpell.SPELL_BY_NAME.keySet())) {
            if (!allNames.contains(s)) {
                PKSpell.SPELL_BY_NAME.remove(s);
            }
        }

        PKMagicSpells.magicSpellsConfig.save();

        for (PKSpell value : PKSpell.SPELL_BY_NAME.values()) {
            injectAbility(value);
        }

        Bukkit.getLogger().log(Level.INFO, " §2§l--- §r§aDONE §2§l---");
    }

    private static Map<String, CoreAbility> ABILITIES_BY_NAME;

    private static Map<Class<? extends CoreAbility>, CoreAbility> ABILITIES_BY_CLASS;

    static {
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            Field name = CoreAbility.class.getDeclaredField("ABILITIES_BY_NAME");
            name.setAccessible(true);
            modifiersField.setInt(name, name.getModifiers() & ~Modifier.FINAL);
            ABILITIES_BY_NAME = (Map<String, CoreAbility>) name.get(null);

            Field clazz = CoreAbility.class.getDeclaredField("ABILITIES_BY_CLASS");
            clazz.setAccessible(true);
            modifiersField.setInt(clazz, clazz.getModifiers() & ~Modifier.FINAL);
            ABILITIES_BY_CLASS = (Map<Class<? extends CoreAbility>, CoreAbility>) clazz.get(null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void injectAbility(PKSpell spell) {
        try {

            Class<? extends PKSpellAbility> type = new ByteBuddy()
                    .redefine(PKSpellAbilityExtend.class)
                    .name("PKSpell" + spell.getSpell().getInternalName().replaceAll(" ", ""))
                    .make()
                    .load(PKSpellAbilityExtend.class.getClassLoader())
                    .getLoaded();
            PKSpellAbility instance = type.getDeclaredConstructor(PKSpell.class).newInstance(spell);

            ABILITIES_BY_NAME.put(spell.getName().toLowerCase(), instance);
            ABILITIES_BY_CLASS.put(type, instance);


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReload() {
        load();
    }

    @Override
    public PluginCore getCorePlugin() {
        return plugin;
    }
}
