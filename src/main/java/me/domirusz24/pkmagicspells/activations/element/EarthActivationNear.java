package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("EARTH_NEAR")
public class EarthActivationNear extends AbilityActivation {
    public EarthActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return EarthAbility.getNearbyEarthBlock(player.getPlayer().getLocation(), 3, 3) != null;
    }
}
