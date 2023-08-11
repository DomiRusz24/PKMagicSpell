package me.domirusz24.pkmagicspells;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.event.BendingPlayerCreationEvent;
import com.projectkorra.projectkorra.event.PlayerBindChangeEvent;
import lombok.RequiredArgsConstructor;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;
import me.domirusz24.pkmagicspells.managers.SpellBenderManager;
import me.domirusz24.pkmagicspells.model.SpellBender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PKMagicSpellsListener implements Listener {

    private final SpellBenderManager spellBenderManager;
    private final AbilityActivationManager activationManager;

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
