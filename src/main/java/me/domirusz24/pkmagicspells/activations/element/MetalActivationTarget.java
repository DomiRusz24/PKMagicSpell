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

import com.projectkorra.projectkorra.ability.MetalAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("METAL_TARGET")
public class MetalActivationTarget extends AbilityActivation {
    public MetalActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return MetalAbility.isMetalbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 4).getType());
    }
}
