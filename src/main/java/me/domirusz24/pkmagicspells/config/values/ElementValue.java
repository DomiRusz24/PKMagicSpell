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

import com.projectkorra.projectkorra.Element;
import me.domirusz24.ext.config.internal.AbstractConfigValue;
import me.domirusz24.ext.config.internal.ConfigValueHolder;

public class ElementValue extends AbstractConfigValue<Element, ElementValue> {
    public ElementValue(String path, Element defaultValue, ConfigValueHolder config) {
        super(path, defaultValue, config);
    }

    @Override
    public void _setValue(Element element) {
        getConfig().set(getPath(), element.getName());
    }

    @Override
    public void _setDefaultValue(Element element) {
        getConfig().addDefault(getPath(), element.getName());
    }

    @Override
    protected Element getConfigValue() {
        return Element.getElement(getConfig().getString(getPath()));
    }
}
