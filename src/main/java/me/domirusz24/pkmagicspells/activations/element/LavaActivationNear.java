package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.LavaAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import me.domirusz24.plugincore.util.UtilMethods;
import org.bukkit.Material;

@AbilityActivationData("LAVA_NEAR")
public class LavaActivationNear extends AbilityActivation {
    public LavaActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return !UtilMethods.getNearbyBlocks(player.getPlayer().getLocation(), 3, Material.LAVA).isEmpty();
    }
}

