package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.MetalAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("METAL_TARGET")
public class MetalActivationTarget extends AbilityActivation {
    public MetalActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return MetalAbility.isMetalbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 4).getType());
    }
}
