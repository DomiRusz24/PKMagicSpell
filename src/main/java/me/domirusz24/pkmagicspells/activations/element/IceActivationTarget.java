package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.IceAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("ICE_TARGET")
public class IceActivationTarget extends AbilityActivation {
    public IceActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return IceAbility.isIcebendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 5).getType(), true);
    }
}
