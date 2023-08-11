package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.PlantAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("PLANT_TARGET")
public class PlantActivationTarget extends AbilityActivation {
    public PlantActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return PlantAbility.isPlant(player.getPlayer().getTargetBlock(null, 4).getType());
    }
}