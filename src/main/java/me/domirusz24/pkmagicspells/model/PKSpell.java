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
        return name.getValue();
    }

    public List<Class<? extends AbilityActivation>> getActivations() {
        return activation.getValue();
    }
}
