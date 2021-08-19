package me.domirusz24.pkmagicspells.pk;

import com.nisovin.magicspells.Spell;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.ElementalAbility;
import me.domirusz24.pkmagicspells.PKMagicSpells;
import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.values.ActivationsValue;
import me.domirusz24.pkmagicspells.config.values.ElementValue;
import me.domirusz24.plugincore.config.configvalue.ConfigValue;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PKSpell {

    public static HashMap<String, PKSpell> SPELL_BY_NAME = new HashMap<>();

    private final Spell spell;

    private final ActivationsValue activation;

    private final ElementValue element;

    private final ConfigValue<String> description;

    private final ConfigValue<String> instruction;

    private final ConfigValue<String> name;

    public PKSpell(Spell spell) {
        this.spell = spell;
        activation = new ActivationsValue("Spell." + spell.getInternalName() + ".activation", Collections.singletonList(LeftClickActivation.class), PKMagicSpells.magicSpellsConfig, true);
        element = new ElementValue("Spell." + spell.getInternalName() + ".element", Element.AIR, PKMagicSpells.magicSpellsConfig, true);
        description = new ConfigValue<>("Spell." + spell.getInternalName() + ".description", ".", PKMagicSpells.magicSpellsConfig, true);
        instruction = new ConfigValue<>("Spell." + spell.getInternalName() + ".instruction", ".", PKMagicSpells.magicSpellsConfig, true);
        name = new ConfigValue<>("Spell." + spell.getInternalName() + ".name", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', spell.getName().replaceAll(" ", ""))), PKMagicSpells.magicSpellsConfig, true);
        SPELL_BY_NAME.put(spell.getInternalName(), this);
    }

    public Spell getSpell() {
        return spell;
    }

    public Element getElement() {
        return element.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String getInstruction() {
        return instruction.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Class<? extends AbilityActivation>> getActivations() {
        return activation.getValue();
    }
}
