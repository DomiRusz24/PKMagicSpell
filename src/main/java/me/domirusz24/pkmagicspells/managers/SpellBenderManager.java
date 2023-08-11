package me.domirusz24.pkmagicspells.managers;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import lombok.RequiredArgsConstructor;
import me.domirusz24.ext.util.suppliers.CacheSupplier;
import me.domirusz24.pkmagicspells.model.SpellBender;
import me.domirusz24.pkmagicspells.projectkorra.PKSpellAbility;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class SpellBenderManager extends CacheSupplier<Player, SpellBender> {
    private final AbilityActivationManager manager;

    public void updateSlot(Player player, int slot) {
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        if (bPlayer == null) return;

        String currentAbilityName = bPlayer.getAbilities().get(slot);
        if (currentAbilityName == null || currentAbilityName.isEmpty()) {
            removeSlot(player);
            return;
        }

        getNow(bPlayer.getPlayer()).ifPresent(spellBender -> {
            CoreAbility ability = CoreAbility.getAbility(currentAbilityName);

            if (ability instanceof PKSpellAbility) {
                PKSpellAbility spellAbility = (PKSpellAbility) ability;
                spellBender.setCurrentSpell(spellAbility, manager.createInstances(spellBender, spellAbility.getSpell().getActivations()));
            } else {
                removeSlot(player);
            }
        });
    }

    public void removeSlot(Player player) {
        getNow(player).ifPresent(spellBender -> {
            Optional.ofNullable(spellBender.getActivationList()).ifPresent(manager::unregisterInstances);
            spellBender.removeCurrentSpell();
        });
    }

    @Override
    public CompletableFuture<Optional<SpellBender>> remove(Player key) {
        return super.remove(key).thenApply(oBender -> oBender.map(bender -> {
            removeSlot(bender.getPlayer());
            return bender;
        }));
    }
}
