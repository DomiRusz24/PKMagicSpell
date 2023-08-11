package me.domirusz24.pkmagicspells.model;

import com.projectkorra.projectkorra.BendingPlayer;
import lombok.Getter;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbility;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class SpellBender {

    private final BendingPlayer bPlayer;

    public SpellBender(BendingPlayer bPlayer) {
        this.bPlayer = bPlayer;
    }

    @Getter
    private List<AbilityActivation> activationList;

    @Getter
    private Optional<PKSpellAbility> currentSpell = Optional.empty();

    public void setCurrentSpell(PKSpellAbility currentSpell, List<AbilityActivation> activationList) {
        stopActivators();
        if (currentSpell == null) return;

        this.currentSpell = Optional.of(currentSpell);
        this.activationList = activationList;
    }

    public void removeCurrentSpell() {
        stopActivators();
        currentSpell = Optional.empty();
    }

    public boolean tryCasting() {
        return currentSpell.map(spell -> {
            for (AbilityActivation ability : activationList) {
                if (!ability.isFulfilled()) return false;
            }

            if (bPlayer.isOnCooldown(spell)) return false;

            cast();

            return true;
        }).orElse(true);
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
        currentSpell.ifPresent(spell -> {
            spell.getSpell().getSpell().cast(bPlayer.getPlayer());
            bPlayer.addCooldown(spell, (long) spell.getSpell().getSpell().getCooldown() * 1000L);
        });
    }

    public boolean hasSpell() {
        return currentSpell.isPresent();
    }

    public Player getPlayer() {
        return bPlayer.getPlayer();
    }

}
