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

package me.domirusz24.pkmagicspells.projectkorra;

import me.domirusz24.pkmagicspells.model.PKSpell;
import org.bukkit.entity.Player;

public class PKSpellAbilityExtend extends PKSpellAbility {
    public PKSpellAbilityExtend(PKSpell spell) {
        super(spell);
    }

    public PKSpellAbilityExtend(Player player, PKSpell spell) {
        super(player, spell);
    }
}
