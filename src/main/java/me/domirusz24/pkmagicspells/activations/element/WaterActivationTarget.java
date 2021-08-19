package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.SandAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("WATER_TARGET")
public class WaterActivationTarget extends AbilityActivation {
    public WaterActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return WaterAbility.isWater(player.getPlayer().getTargetBlock(null, 5));
    }
}

