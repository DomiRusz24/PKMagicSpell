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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@AbilityActivationData("RIGHT_CLICK")
public class RightClickActivation extends AbilityActivation {
    public RightClickActivation(SpellBender player) {
        super(player);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!event.getPlayer().equals(player.getPlayer())) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            setFulfill(true);
            setFulfill(false);
        }
    }
}
