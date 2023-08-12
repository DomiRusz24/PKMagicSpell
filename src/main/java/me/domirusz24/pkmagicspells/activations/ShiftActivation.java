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

package me.domirusz24.pkmagicspells.activations;

import me.domirusz24.pkmagicspells.model.SpellBender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;


@AbilityActivationData("SHIFT")
public class ShiftActivation extends AbilityActivation {
    public ShiftActivation(SpellBender player) {
        super(player);
    }

    @EventHandler
    public void onCrouch(PlayerToggleSneakEvent event) {
        if (!event.getPlayer().equals(player.getPlayer())) return;
        if (event.isSneaking()) {
            setFulfill(true);
        } else {
            setFulfill(false);
        }
    }
}
