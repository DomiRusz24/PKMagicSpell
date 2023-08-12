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

package me.domirusz24.pkmagicspells.model;

import com.nisovin.magicspells.Spell;
import com.projectkorra.projectkorra.Element;
import me.domirusz24.ext.config.internal.ConfigValueHolder;
import me.domirusz24.ext.config.values.CColorString;
import me.domirusz24.ext.config.values.CMappedObject;
import me.domirusz24.ext.config.values.CString;
import me.domirusz24.pkmagicspells.activations.AbilityActivation;
import me.domirusz24.pkmagicspells.activations.LeftClickActivation;
import me.domirusz24.pkmagicspells.config.values.ActivationsValue;
import me.domirusz24.pkmagicspells.config.values.ElementValue;
import me.domirusz24.pkmagicspells.managers.AbilityActivationManager;
import net.md_5.bungee.api.ChatColor;

import java.util.Collections;
import java.util.List;

public class PKSpell extends CMappedObject<Spell> {

    private final ActivationsValue activation;
    private final ElementValue element = new ElementValue("Element", Element.AIR, this);

    private final CColorString description = new CColorString("Description", ".", this);

    private final CColorString instruction = new CColorString("Instruction", ".", this);

    private final CString name = new CString("Name", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', getSpell().getName().replaceAll(" ", ""))), this);

    public PKSpell(String path, Spell key, ConfigValueHolder config, AbilityActivationManager activationManager) {
        super(path, key, config);
        activation = new ActivationsValue("Activation", Collections.singletonList(LeftClickActivation.class), this, activationManager);
    }

    public ActivationsValue getConfigActivation() {
        return activation;
    }

    public Spell getSpell() {
        return getKey();
    }

    public Element getElement() {
        return element.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String getInstruction() {
        return instruction.getValue();
    }

    public String getName() {
        return name.getValue() == null ? ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', getSpell().getName().replaceAll(" ", ""))) : name.getValue();
    }

    public List<Class<? extends AbilityActivation>> getActivations() {
        return activation.getValue();
    }
}
