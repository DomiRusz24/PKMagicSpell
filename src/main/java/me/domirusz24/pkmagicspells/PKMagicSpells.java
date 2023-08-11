package me.domirusz24.pkmagicspells;

import me.domirusz24.ext.config.ConfigExtension;
import me.domirusz24.ext.language.LanguageExtension;
import me.domirusz24.ext.util.UtilPlugin;
import me.domirusz24.pkmagicspells.config.MagicSpellsConfig;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;
import me.domirusz24.pkmagicspells.managers.MagicSpellsManager;
import me.domirusz24.pkmagicspells.managers.SpellBenderManager;
import org.bukkit.Bukkit;

public final class PKMagicSpells extends UtilPlugin {

    @Override
    protected void setUpExtensions() {
        ConfigExtension.onLoad(new ConfigExtension.Configuration(this, getLogger(), () -> setEnabled(false)));

        LanguageExtension.onLoad(new LanguageExtension.Configuration(
                getLogger(),
                "language.yml"
        ));

        LanguageExtension.getConfiguration().registerAllAnnotations(this);
    }


    private MagicSpellsConfig magicSpellsConfig;

    @Override
    protected void setUpConfigs() {
        activationManager = new AbilityActivationManager(eventControl);
        magicSpellsConfig = new MagicSpellsConfig("MagicSpells.yml", activationManager);
    }

    private MagicSpellsManager magicSpellsManager;
    private AbilityActivationManager activationManager;
    private SpellBenderManager benderSupplier;

    @Override
    protected void setUpManagers() {
        benderSupplier = new SpellBenderManager(activationManager);
        Bukkit.getScheduler().runTask(this, () -> {
            magicSpellsManager = new MagicSpellsManager(magicSpellsConfig);
            magicSpellsManager.load();
        });
    }

    @Override
    protected void setUpListeners() {
        register(new PKMagicSpellsListener(benderSupplier, activationManager));
    }

    @Override
    protected void setUpCommands() {}
}
