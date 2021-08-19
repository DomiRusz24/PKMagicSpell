package me.domirusz24.pkmagicspells.pk;

import org.bukkit.entity.Player;

public class PKSpellAbilityExtend extends PKSpellAbility {
    public PKSpellAbilityExtend(PKSpell spell) {
        super(spell);
    }

    public PKSpellAbilityExtend(Player player, PKSpell spell) {
        super(player, spell);
    }
}
