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

package me.domirusz24.pkmagicspells;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.event.BendingPlayerCreationEvent;
import com.projectkorra.projectkorra.event.PlayerBindChangeEvent;
import lombok.RequiredArgsConstructor;
import me.domirusz24.pkmagicspells.managers.SpellBenderManager;
import me.domirusz24.pkmagicspells.model.SpellBender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PKMagicSpellsListener implements Listener {

    private final SpellBenderManager spellBenderManager;

    @EventHandler
    public void onJoin(BendingPlayerCreationEvent event) {
        spellBenderManager.put(event.getBendingPlayer().getPlayer(), new SpellBender(event.getBendingPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        spellBenderManager.remove(event.getPlayer());
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent event) {
        int slot = event.getNewSlot() + 1;

        spellBenderManager.updateSlot(event.getPlayer(), slot);
    }

    @EventHandler
    public void onSlotChange(PlayerBindChangeEvent event) {
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(event.getPlayer());
        if (bPlayer == null) return;
        if (bPlayer.getAbilities() == null) return;
        int slot = event.getSlot();
        if (slot != event.getPlayer().getInventory().getHeldItemSlot() + 1) return;

        spellBenderManager.updateSlot(event.getPlayer(), slot);
    }
}
