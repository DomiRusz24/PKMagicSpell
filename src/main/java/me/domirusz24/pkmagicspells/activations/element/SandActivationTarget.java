package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.SandAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import org.bukkit.block.Block;

@AbilityActivationData("SAND_TARGET")
public class SandActivationTarget extends AbilityActivation {
    public SandActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return SandAbility.isSandbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 4).getType());
    }
}
