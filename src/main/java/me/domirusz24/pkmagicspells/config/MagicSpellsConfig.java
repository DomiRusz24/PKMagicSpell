package me.domirusz24.pkmagicspells.config;

import com.nisovin.magicspells.Spell;
import com.projectkorra.projectkorra.Element;
import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.pk.PKSpell;
import me.domirusz24.plugincore.PluginCore;
import me.domirusz24.plugincore.config.AbstractConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MagicSpellsConfig extends AbstractConfig {
    public MagicSpellsConfig(String path, PluginCore plugin) {
        super(path, plugin);
    }

    @Override
    protected boolean autoGenerate() {
        return true;
    }
}
