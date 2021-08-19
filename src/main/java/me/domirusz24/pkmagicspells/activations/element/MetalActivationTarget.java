package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.MetalAbility;
import com.projectkorra.projectkorra.ability.SandAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("METAL_TARGET")
public class MetalActivationTarget extends AbilityActivation {
    public MetalActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return MetalAbility.isMetalbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 4).getType());
    }
}
