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

package me.domirusz24.pkmagicspells.config.values;

import me.domirusz24.ext.config.internal.AbstractConfigValue;
import me.domirusz24.ext.config.internal.ConfigValueHolder;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ActivationsValue extends AbstractConfigValue<List<Class<? extends AbilityActivation>>, ActivationsValue> {

    private final AbilityActivationManager manager;

    public ActivationsValue(String path, List<Class<? extends AbilityActivation>> defaultValue, ConfigValueHolder config, AbilityActivationManager manager) {
        super(path, defaultValue, config);
        this.manager = manager;
        reload();
    }

    @Override
    public void _setValue(List<Class<? extends AbilityActivation>> classes) {
        if (manager == null) return;
        getConfig().set(getPath(), classes.stream().map(manager::getName).collect(Collectors.toList()));
    }

    @Override
    public void _setDefaultValue(List<Class<? extends AbilityActivation>> classes) {
        if (manager == null) return;
        getConfig().addDefault(getPath(), classes.stream().map(manager::getName).collect(Collectors.toList()));
    }

    @Override
    protected List<Class<? extends AbilityActivation>> getConfigValue() {
        if (manager == null) return Collections.singletonList(LeftClickActivation.class);
        List<Class<? extends AbilityActivation>> activations = new ArrayList<>();
        for (String s : getConfig().getStringList(getPath())) {
            manager.getNow(s).ifPresent(activations::add);
        }

        if (activations.isEmpty()) {
            activations.add(LeftClickActivation.class);
        }

        return activations;
    }
}
