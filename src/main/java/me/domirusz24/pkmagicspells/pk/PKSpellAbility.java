package me.domirusz24.pkmagicspells.pk;

import com.nisovin.magicspells.Spell;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.ElementalAbility;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PKSpellAbility extends ElementalAbility {

    private final PKSpell spell;

    public PKSpellAbility(PKSpell spell) {
        super(null);
        this.spell = spell;
    }

    public PKSpellAbility(Player player, PKSpell spell) {
        super(player);
        this.spell = spell;
    }

    @Override
    public void progress() {

    }

    @Override
    public boolean isSneakAbility() {
        return false;
    }

    @Override
    public boolean isHarmlessAbility() {
        return false;
    }

    @Override
    public boolean isIgniteAbility() {
        return false;
    }

    @Override
    public boolean isExplosiveAbility() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public long getCooldown() {
        return ((long) spell.getSpell().getCooldown() * 1000L);
    }

    @Override
    public String getName() {
        return spell.getName();
    }

    @Override
    public boolean isHiddenAbility() {
        return spell.getSpell().isHelperSpell();
    }

    @Override
    public String getInstructions() {
        return spell.getInstruction();
    }

    public String getInternalName() {
        return spell.getSpell().getInternalName();
    }

    public PKSpell getSpell() {
        return spell;
    }

    @Override
    public String getDescription() {
        return spell.getDescription();
    }

    @Override
    public Element getElement() {
        return spell.getElement();
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
