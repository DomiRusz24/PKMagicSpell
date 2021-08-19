package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.LavaAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("LAVA_TARGET")
public class LavaActivationTarget extends AbilityActivation {
    public LavaActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return LavaAbility.isLava(player.getPlayer().getTargetBlock(null, 5));
    }
}
