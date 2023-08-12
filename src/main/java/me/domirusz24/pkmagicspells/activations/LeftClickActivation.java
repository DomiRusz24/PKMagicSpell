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
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

@AbilityActivationData("LEFT_CLICK")
public class LeftClickActivation extends AbilityActivation {
    public LeftClickActivation(SpellBender player) {
        super(player);
    }

    @EventHandler
    public void onLeftClick(PlayerAnimationEvent event) {
        if (event.isCancelled() || !event.getPlayer().equals(player.getPlayer())) return;
        if (event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {
            setFulfill(true);
            setFulfill(false);
        }
    }
}
