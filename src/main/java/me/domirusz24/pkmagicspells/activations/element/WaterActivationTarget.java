package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("WATER_TARGET")
public class WaterActivationTarget extends AbilityActivation {
    public WaterActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return WaterAbility.isWaterbendable(player.getPlayer().getTargetBlock(null, 5).getType());
    }
}

