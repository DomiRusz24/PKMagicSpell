package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("EARTH_TARGET")
public class EarthActivationTarget extends AbilityActivation {
    public EarthActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return EarthAbility.isEarthbendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 5));
    }
}
