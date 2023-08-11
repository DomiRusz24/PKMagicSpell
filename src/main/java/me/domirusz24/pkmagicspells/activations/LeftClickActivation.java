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
