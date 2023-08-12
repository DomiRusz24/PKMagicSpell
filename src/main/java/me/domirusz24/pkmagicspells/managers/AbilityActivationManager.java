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

package me.domirusz24.pkmagicspells.managers;

import me.domirusz24.ext.util.EventControl;
import me.domirusz24.ext.util.suppliers.CacheSupplier;
import me.domirusz24.pkmagicspells.activations.*;
import me.domirusz24.pkmagicspells.activations.element.*;
import me.domirusz24.pkmagicspells.model.SpellBender;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AbilityActivationManager extends CacheSupplier<String, Class<? extends AbilityActivation>> {

    private final EventControl control;

    public AbilityActivationManager(EventControl control) {
        defaultActivators();
        this.control = control;
    }

    public void defaultActivators() {
        register(LeftClickActivation.class);
        register(RightClickActivation.class);
        register(ShiftActivation.class);

        register(EarthActivationNear.class);
        register(EarthActivationTarget.class);

        register(SandActivationTarget.class);

        register(WaterActivationNear.class);
        register(WaterActivationTarget.class);

        register(PlantActivationTarget.class);

        register(MetalActivationTarget.class);

        register(IceActivationNear.class);
        register(IceActivationTarget.class);

        register(LavaActivationNear.class);
        register(LavaActivationTarget.class);
    }

    public String getName(Class<? extends AbilityActivation> clazz) {
        if (clazz.isAnnotationPresent(AbilityActivationData.class)) {
            return clazz.getAnnotation(AbilityActivationData.class).value();
        } else {
            return null;
        }
    }

    public void register(Class<? extends AbilityActivation> clazz) {
        String name = getName(clazz);
        put(name, clazz);
        Bukkit.getLogger().log(Level.INFO, "|- Registered " + name + ".");
    }

    public List<AbilityActivation> createInstances(SpellBender player, List<Class<? extends AbilityActivation>> activations) {
        List<AbilityActivation> instances = new ArrayList<>();
        for (Class<? extends AbilityActivation> activation : activations) {
            try {
                AbilityActivation abilityActivation = activation.getDeclaredConstructor(SpellBender.class).newInstance(player);
                control.register(abilityActivation);
                instances.add(abilityActivation);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instances;
    }

    public void unregisterInstances(List<AbilityActivation> activations) {
        for (AbilityActivation activation : activations) {
            control.unregister(activation);
        }
    }
}
