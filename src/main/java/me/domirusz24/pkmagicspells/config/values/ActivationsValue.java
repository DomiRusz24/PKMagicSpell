package me.domirusz24.pkmagicspells.config.values;

import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.config.AbilityActivation;
import me.domirusz24.plugincore.config.AbstractConfig;
import me.domirusz24.plugincore.config.configvalue.AbstractConfigValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivationsValue extends AbstractConfigValue<List<Class<? extends AbilityActivation>>> {
    public ActivationsValue(String path, List<Class<? extends AbilityActivation>> defaultValue, AbstractConfig config) {
        super(path, defaultValue, config);
    }

    public ActivationsValue(String path, List<Class<? extends AbilityActivation>> defaultValue, AbstractConfig config, boolean autoReload) {
        super(path, defaultValue, config, autoReload);
    }

    @Override
    public void _setValue(List<Class<? extends AbilityActivation>> classes) {
        getConfig().set(getPath(), classes.stream().map(AbilityActivation::getName).collect(Collectors.toList()));
    }

    @Override
    public void _setDefaultValue(List<Class<? extends AbilityActivation>> classes) {
        getConfig().addDefault(getPath(), classes.stream().map(AbilityActivation::getName).collect(Collectors.toList()));
    }

    @Override
    protected List<Class<? extends AbilityActivation>> getConfigValue() {
        List<Class<? extends AbilityActivation>> activations = new ArrayList<>();
        for (String s : getConfig().getStringList(getPath())) {
            Class<? extends AbilityActivation> clazz = AbilityActivation.ACTIVATION_BY_NAME.getOrDefault(s, null);
            if (clazz == null) continue;
            activations.add(clazz);
        }

        if (activations.isEmpty()) {
            activations.add(LeftClickActivation.class);
        }
        return activations;
    }
}
