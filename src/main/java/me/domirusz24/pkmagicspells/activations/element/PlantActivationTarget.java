package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.PlantAbility;
import com.projectkorra.projectkorra.ability.SandAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("PLANT_TARGET")
public class PlantActivationTarget extends AbilityActivation {
    public PlantActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return PlantAbility.isPlant(player.getPlayer().getTargetBlock(null, 4).getType());
    }
}