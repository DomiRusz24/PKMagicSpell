package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.IceAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;
@AbilityActivationData("ICE_NEAR")
public class IceActivationNear extends AbilityActivation {
    public IceActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return IceAbility.getIceSourceBlock(player.getPlayer(), 3) != null;
    }
}
