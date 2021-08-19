package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import me.domirusz24.plugincore.util.UtilMethods;
import org.bukkit.Material;

@AbilityActivationData("WATER_NEAR")
public class WaterActivationNear extends AbilityActivation {
    public WaterActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return !UtilMethods.getNearbyBlocks(player.getPlayer().getLocation(), 3, Material.WATER).isEmpty();
    }
}
