package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.SandAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("SAND_TARGET")
public class SandActivationTarget extends AbilityActivation {
    public SandActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return SandAbility.isSandbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 4).getType());
    }
}
