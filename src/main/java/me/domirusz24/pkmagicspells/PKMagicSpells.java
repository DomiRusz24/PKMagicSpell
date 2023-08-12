/*
 * Copyright (C) 2023 DomiRusz24
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 */

package me.domirusz24.pkmagicspells;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import me.domirusz24.ext.config.ConfigExtension;
import me.domirusz24.ext.language.BasicLanguage;
import me.domirusz24.ext.language.LanguageExtension;
import me.domirusz24.ext.util.UtilPlugin;
import me.domirusz24.pkmagicspells.config.MagicSpellsConfig;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;
import me.domirusz24.pkmagicspells.managers.MagicSpellsManager;
import me.domirusz24.pkmagicspells.managers.SpellBenderManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public final class PKMagicSpells extends UtilPlugin {

    public static final String SOURCE_CODE = "https://github.com/DomiRusz24/PKMagicSpell/";

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(false).silentLogs(true));
    }

    private static final String DISCLOSURE = "PKMagicSpells  Copyright (C) 2023  DomiRusz24\n" +
            "    This program comes with ABSOLUTELY NO WARRANTY; for details execute `pkspell sourcecode'.\n" +
            "    This is free software, and you are welcome to redistribute it\n" +
            "    under certain conditions; execute `pkspell sourcecode' for details.";

    @Override
    protected void setUpExtensions() {

        getLogger().log(Level.INFO, DISCLOSURE);

        CommandAPI.onEnable();
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
        register(new PKMagicSpellsListener(benderSupplier));
    }

    @Override
    protected void setUpCommands() {
        new CommandAPICommand("pkspell")
                .executes((p, info) -> {
                    sendPluginInfo(p);
                })
                .withSubcommands(
                        new CommandAPICommand("reload")
                                .withPermission("pkspell.admin")
                                .executes((p, info) -> {
                                    if (ConfigExtension.getConfiguration().getManager().reloadConfigs()) {
                                        p.sendMessage(BasicLanguage.SUCCESS);
                                    } else {
                                        p.sendMessage(BasicLanguage.FAILURE);
                                    }
                                }),
                        new CommandAPICommand("sourcecode")
                                .executes((p, info) -> {
                                    sendPluginInfo(p);
                                })
                ).register();
    }

    private void sendPluginInfo(CommandSender p) {
        p.sendMessage("PKMagicSpells  Copyright (C) 2023  DomiRusz24");
        p.sendMessage("Source code:");
        TextComponent component = new TextComponent(SOURCE_CODE);
        component.setItalic(true);
        component.setColor(ChatColor.AQUA);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, SOURCE_CODE));
        p.spigot().sendMessage(component);
    }
}
