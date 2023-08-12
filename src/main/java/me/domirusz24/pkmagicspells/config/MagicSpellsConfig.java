/*
 * Copyright (C) 2023 DomiRusz24
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 */

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

    private static final String EXAMPLE_CONFIG =
            "Spells:\n" +
            "  INTERNAL_NAME:\n" +
            "    Element: Air\n" +
            "    Description: 'Description'\n" +
            "    Instruction: 'Instruction'\n" +
            "    Name: 'Displayed Name'\n" +
            "    # One or multiple of these can be used, by default 'LEFT_CLICK'\n" +
            "    Activation:\n" +
            "    - WATER_NEAR\n" +
            "    - WATER_TARGET\n" +
            "    - EARTH_TARGET\n" +
            "    - LAVA_TARGET\n" +
            "    - ICE_NEAR\n" +
            "    - PLANT_TARGET\n" +
            "    - ICE_TARGET\n" +
            "    - EARTH_NEAR\n" +
            "    - METAL_TARGET\n" +
            "    - SHIFT\n" +
            "    - RIGHT_CLICK\n" +
            "    - LAVA_NEAR\n" +
            "    - SAND_TARGET\n" +
            "    - LEFT_CLICK\n";

    public MagicSpellsConfig(String path, AbilityActivationManager manager) {
        super(path);
        setExampleConfig(EXAMPLE_CONFIG);
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
