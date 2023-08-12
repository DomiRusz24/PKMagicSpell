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

package me.domirusz24.pkmagicspells.model;

import com.projectkorra.projectkorra.BendingPlayer;
import lombok.Getter;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbility;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class SpellBender {

    private final BendingPlayer bPlayer;

    public SpellBender(BendingPlayer bPlayer) {
        this.bPlayer = bPlayer;
    }

    @Getter
    private List<AbilityActivation> activationList;

    @Getter
    private Optional<PKSpellAbility> currentSpell = Optional.empty();

    public void setCurrentSpell(PKSpellAbility currentSpell, List<AbilityActivation> activationList) {
        stopActivators();
        if (currentSpell == null) return;

        this.currentSpell = Optional.of(currentSpell);
        this.activationList = activationList;
    }

    public void removeCurrentSpell() {
        stopActivators();
        currentSpell = Optional.empty();
    }

    public boolean tryCasting() {
        return currentSpell.map(spell -> {
            for (AbilityActivation ability : activationList) {
                if (!ability.isFulfilled()) return false;
            }

            if (bPlayer.isOnCooldown(spell)) return false;

            cast();

            return true;
        }).orElse(true);
    }

    private void stopActivators() {
        if (activationList != null) {
            for (AbilityActivation ability : activationList) {
                ability.unregister();
            }
            activationList.clear();
            activationList = null;
        }
    }

    public void cast() {
        currentSpell.ifPresent(spell -> {
            spell.getSpell().getSpell().cast(bPlayer.getPlayer());
            bPlayer.addCooldown(spell, (long) spell.getSpell().getSpell().getCooldown() * 1000L);
        });
    }

    public boolean hasSpell() {
        return currentSpell.isPresent();
    }

    public Player getPlayer() {
        return bPlayer.getPlayer();
    }

}
