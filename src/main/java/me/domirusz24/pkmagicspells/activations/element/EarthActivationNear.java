package me.domirusz24.pkmagicspells.activations.element;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.configuration.ConfigManager;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivationData;
import me.domirusz24.pkmagicspells.pk.SpellBender;
import me.domirusz24.plugincore.util.UtilMethods;
import org.bukkit.Material;

@AbilityActivationData("EARTH_NEAR")
public class EarthActivationNear extends AbilityActivation {
    public EarthActivationNear(SpellBender player) {
        super(player);
    }

    @Override
    public boolean isReady() {
        return !UtilMethods.getNearbyBlocks(player.getPlayer().getLocation(), 3, EarthAbility::isEarth).isEmpty();
    }
}
