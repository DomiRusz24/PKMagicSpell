package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.PlantAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import me.domirusz24.plugincore.util.UtilMethods;

@AbilityActivationData("PLANT_NEAR")
public class PlantActivationNear extends AbilityActivation {
    public PlantActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return !UtilMethods.getNearbyBlocks(player.getPlayer().getLocation(), 3, PlantAbility::isPlant).isEmpty();
    }
}
