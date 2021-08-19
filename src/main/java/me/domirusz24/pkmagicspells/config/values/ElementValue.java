package me.domirusz24.pkmagicspells.config.values;

import com.projectkorra.projectkorra.Element;
import me.domirusz24.plugincore.config.AbstractConfig;
import me.domirusz24.plugincore.config.configvalue.AbstractConfigValue;

public class ElementValue extends AbstractConfigValue<Element> {
    public ElementValue(String path, Element defaultValue, AbstractConfig config) {
        super(path, defaultValue, config);
    }

    public ElementValue(String path, Element defaultValue, AbstractConfig config, boolean autoReload) {
        super(path, defaultValue, config, autoReload);
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
