package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("WATER_NEAR")
public class WaterActivationNear extends AbilityActivation {
    public WaterActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return WaterAbility.getWaterSourceBlock(player.getPlayer(), 3, false) != null;
    }
}
