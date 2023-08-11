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
