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

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import lombok.RequiredArgsConstructor;
import me.domirusz24.ext.util.suppliers.CacheSupplier;
import me.domirusz24.pkmagicspells.model.SpellBender;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbility;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class SpellBenderManager extends CacheSupplier<Player, SpellBender> {
    private final AbilityActivationManager manager;

    public void updateSlot(Player player, int slot) {
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        if (bPlayer == null) return;

        String currentAbilityName = bPlayer.getAbilities().get(slot);
        if (currentAbilityName == null || currentAbilityName.isEmpty()) {
            removeSlot(player);
            return;
        }

        getNow(bPlayer.getPlayer()).ifPresent(spellBender -> {
            CoreAbility ability = CoreAbility.getAbility(currentAbilityName);

            if (ability instanceof PKSpellAbility) {
                PKSpellAbility spellAbility = (PKSpellAbility) ability;
                spellBender.setCurrentSpell(spellAbility, manager.createInstances(spellBender, spellAbility.getSpell().getActivations()));
            } else {
                removeSlot(player);
            }
        });
    }

    public void removeSlot(Player player) {
        getNow(player).ifPresent(spellBender -> {
            Optional.ofNullable(spellBender.getActivationList()).ifPresent(manager::unregisterInstances);
            spellBender.removeCurrentSpell();
        });
    }

    @Override
    public CompletableFuture<Optional<SpellBender>> remove(Player key) {
        return super.remove(key).thenApply(oBender -> oBender.map(bender -> {
            removeSlot(bender.getPlayer());
            return bender;
        }));
    }
}
