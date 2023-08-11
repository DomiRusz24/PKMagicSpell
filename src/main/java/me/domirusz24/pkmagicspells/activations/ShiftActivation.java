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
