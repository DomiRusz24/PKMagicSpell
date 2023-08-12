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

package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.LavaAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("LAVA_NEAR")
public class LavaActivationNear extends AbilityActivation {
    public LavaActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return LavaAbility.getLavaSourceBlock(player.getPlayer(), 3) != null;
    }
}

