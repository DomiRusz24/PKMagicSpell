package me.domirusz24.pkmagicspells.pk;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import me.domirusz24.pkmagicspells.PKMagicSpells;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.plugincore.core.players.AbstractPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellBender extends AbstractPlayer {

    public static final HashMap<String, SpellBender> SPELL_BENDERS = new HashMap<>();

    private final BendingPlayer bPlayer;

    public SpellBender(Player player, BendingPlayer bPlayer) {
        super(PKMagicSpells.plugin, player);
        this.bPlayer = bPlayer;
        SPELL_BENDERS.put(player.getName(), this);
    }

    private List<AbilityActivation> activationList;

    private PKSpellAbility currentSpell = null;

    public void slotChange(CoreAbility ability) {
        stopActivators();
        if (ability instanceof PKSpellAbility) {
            currentSpell = (PKSpellAbility) ability;
            activationList = AbilityActivation.createInstances(this, currentSpell.getSpell().getActivations());
        } else {
            currentSpell = null;
        }
    }

    public boolean tryCasting() {
        if (!hasSpell()) return true;
        for (AbilityActivation ability : activationList) {
            if (!ability.isReady()) return false;
        }
        if (bPlayer.isOnCooldown(currentSpell)) return false;
        cast();
        return true;
    }

    private void stopActivators() {
        if (activationList != null) {
            for (AbilityActivation ability : activationList) {
                ability.unregister();
            }
            activationList.clear();
            activationList = null;
        }
    }

    public void cast() {
        currentSpell.getSpell().getSpell().cast((LivingEntity) player);
        bPlayer.addCooldown(currentSpell, (long) currentSpell.getSpell().getSpell().getCooldown() * 1000L);
    }

    public boolean hasSpell() {
        return currentSpell != null;
    }


    @Override
    protected void onUnregister() {
        SPELL_BENDERS.remove(player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean resetInventory() {
        return false;
    }

}
