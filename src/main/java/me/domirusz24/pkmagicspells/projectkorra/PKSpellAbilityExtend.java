package me.domirusz24.pkmagicspells.projectkorra;

import me.domirusz24.pkmagicspells.model.PKSpell;
import org.bukkit.entity.Player;

public class PKSpellAbilityExtend extends PKSpellAbility {
    public PKSpellAbilityExtend(PKSpell spell) {
        super(spell);
    }

    public PKSpellAbilityExtend(Player player, PKSpell spell) {
        super(player, spell);
    }
}
