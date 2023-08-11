package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.LavaAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("LAVA_NEAR")
public class LavaActivationNear extends AbilityActivation {
    public LavaActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return LavaAbility.getLavaSourceBlock(player.getPlayer(), 3) != null;
    }
}

