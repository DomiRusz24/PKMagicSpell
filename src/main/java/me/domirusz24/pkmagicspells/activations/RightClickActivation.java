package me.domirusz24.pkmagicspells.activations;

import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
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
            ready(true);
            ready(false);
        }
    }
}
