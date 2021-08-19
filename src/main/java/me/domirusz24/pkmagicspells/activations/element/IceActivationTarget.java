package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.IceAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;

@AbilityActivationData("ICE_TARGET")
public class IceActivationTarget extends AbilityActivation {
    public IceActivationTarget(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return IceAbility.isIcebendable(player.getPlayer(), player.getPlayer().getTargetBlock(null, 5).getType(), true);
    }
}
