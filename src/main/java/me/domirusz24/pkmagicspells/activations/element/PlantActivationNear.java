package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.PlantAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("PLANT_NEAR")
public class PlantActivationNear extends AbilityActivation {
    public PlantActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return PlantAbility.getPlantSourceBlock(player.getPlayer(), 3, false) != null;
    }
}
