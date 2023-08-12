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

package me.domirusz24.pkmagicspells.managers;

import com.projectkorra.projectkorra.ability.CoreAbility;
import me.domirusz24.ext.config.internal.ConfigManager;
import me.domirusz24.pkmagicspells.config.MagicSpellsConfig;
import me.domirusz24.pkmagicspells.model.PKSpell;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbility;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbilityExtend;
import net.bytebuddy.ByteBuddy;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;

public class MagicSpellsManager implements ConfigManager.Reloadable {
    private final MagicSpellsConfig config;

    public MagicSpellsManager(MagicSpellsConfig config) {
        this.config = config;
        registerReloadable();
    }

    public void load() {
        Bukkit.getLogger().log(Level.WARNING, "Loading PKSpellAbilities, THIS MAY TAKE A WHILE!");

        for (PKSpell spell : config.getPkSpells().get().values()) {
            injectAbility(spell);
        }
    }

    private static Map<String, CoreAbility> ABILITIES_BY_NAME;

    private static Map<Class<? extends CoreAbility>, CoreAbility> ABILITIES_BY_CLASS;

    static {
        try {
            Field name = CoreAbility.class.getDeclaredField("ABILITIES_BY_NAME");
            name.setAccessible(true);
            ABILITIES_BY_NAME = (Map<String, CoreAbility>) name.get(null);

            Field clazz = CoreAbility.class.getDeclaredField("ABILITIES_BY_CLASS");
            clazz.setAccessible(true);
            ABILITIES_BY_CLASS = (Map<Class<? extends CoreAbility>, CoreAbility>) clazz.get(null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void injectAbility(PKSpell spell) {

        if (ABILITIES_BY_NAME.containsKey(spell.getSpell().getInternalName())) {
            PKSpellAbility ability = (PKSpellAbility) ABILITIES_BY_NAME.get(spell.getSpell().getInternalName());
            ability.setSpell(spell);
            return;
        }

        try {

            Class<? extends PKSpellAbility> type = new ByteBuddy()
                    .redefine(PKSpellAbilityExtend.class)
                    .name("Ability" + spell.getSpell().getInternalName().replaceAll(" ", ""))
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
}
