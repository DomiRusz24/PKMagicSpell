package me.domirusz24.pkmagicspells;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.event.BendingPlayerCreationEvent;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PKSpellsListener implements Listener {
    @EventHandler
    public void onJoin(BendingPlayerCreationEvent event) {
        new SpellBender(event.getBendingPlayer().getPlayer(), event.getBendingPlayer());
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent event) {
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(event.getPlayer());
        if (bPlayer == null) return;
        if (bPlayer.getAbilities() == null) return;
        int slot = event.getNewSlot() + 1;
        String c = bPlayer.getAbilities().get(slot);
        if (c == null || c.isEmpty()) return;
        CoreAbility ability = CoreAbility.getAbility(c);
        SpellBender.SPELL_BENDERS.get(event.getPlayer().getName()).slotChange(ability);
    }
}
