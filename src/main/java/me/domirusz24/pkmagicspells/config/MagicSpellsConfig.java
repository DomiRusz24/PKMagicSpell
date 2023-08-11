package me.domirusz24.pkmagicspells.config;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;
import me.domirusz24.ext.config.Config;
import me.domirusz24.ext.config.values.CMap;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;
import me.domirusz24.pkmagicspells.model.PKSpell;

import java.util.Optional;

public class MagicSpellsConfig extends Config {

    private final AbilityActivationManager manager;

    private final CMap<Spell, PKSpell> pkSpells;

    public MagicSpellsConfig(String path, AbilityActivationManager manager) {
        super(path);
        this.manager = manager;

        pkSpells = new CMap<>(
                "Spells",
                this,
                ((s, spell, configValueHolder) -> new PKSpell(s, spell, configValueHolder, manager)),
                MagicSpells::getSpellByInternalName,
                Spell::getInternalName
        );

        reload();
        save();
    }

    public CMap<Spell, PKSpell> getPkSpells() {
        return pkSpells;
    }

    public Optional<PKSpell> getSpell(String name) {
        return getSpell(MagicSpells.getSpellByInternalName(name));
    }

    public Optional<PKSpell> getSpell(Spell spell) {
        if (spell == null) return Optional.empty();
        return Optional.ofNullable(pkSpells.get().get(spell));
    }

    @Override
    public boolean reload() {
        return super.reload();
    }

    @Override
    protected boolean autoGenerate() {
        return true;
    }
}
