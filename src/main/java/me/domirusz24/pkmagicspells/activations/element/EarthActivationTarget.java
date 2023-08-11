package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.AbilityActivationData;
import me.domirusz24.pkmagicspells.model.SpellBender;

@AbilityActivationData("EARTH_TARGET")
public class EarthActivationTarget extends AbilityActivation {
    public EarthActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isFulfilled() {
        return EarthAbility.isEarthbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 5));
    }
}
