package me.domirusz24.pkmagicspells;

import com.nisovin.magicspells.MagicSpells;
import me.domirusz24.pkmagicspells.config.MagicSpellsConfig;
import me.domirusz24.pkmagicspells.managers.MagicSpellsManager;
import me.domirusz24.plugincore.CoreListener;
import me.domirusz24.plugincore.PluginCore;
import me.domirusz24.plugincore.core.players.PlayerData;
import me.domirusz24.plugincore.managers.*;
import me.domirusz24.plugincore.managers.database.DataBaseManager;
import me.domirusz24.plugincore.managers.database.DataBaseTable;
import me.domirusz24.plugincore.util.UtilMethods;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public final class PKMagicSpells extends PluginCore {

    public static PKMagicSpells plugin;

    // ***********************

    public static DataBaseManager SqlM;
    public static ConfigManager configM;
    public static CommandManager commandM;
    public static GUIManager guiM;
    public static RegionManager regionM;
    public static WorldEditManager worldEditM;
    public static ChatGUIManager chatGuiM;
    public static ScoreboardManager boardM;
    public static me.domirusz24.plugincore.managers.ProtocolManager nmsM;
    public static SignManager signM;
    public static PAPIManager papiM;
    public static PlayerDataManager playerDataM;

    public static UtilMethods util;

    public static CoreListener listener;

    @Override
    protected void loadDependencies() {
        super.loadDependencies();
    }

    @Override
    protected void loadManagers() {
        super.loadManagers();
        SqlM = super.SqlM;
        configM = super.configM;
        commandM = super.commandM;
        guiM = super.guiM;
        regionM = super.regionM;
        worldEditM = super.worldEditM;
        chatGuiM = super.chatGuiM;
        boardM = super.boardM;
        nmsM = super.nmsM;
        signM = super.signM;
        papiM = super.papiM;
        playerDataM = super.playerDataM;
    }

    @Override
    protected void registerEvents() {
        super.registerEvents();
        listener = super.listener;
    }
    // ***********************

    public static MagicSpellsManager magicSpellsM;

    public static MagicSpellsConfig magicSpellsConfig;

    @Override
    protected void _initialize() {
        plugin = this;
    }

    @Override
    protected void _loadDependencies() {

    }

    @Override
    protected String databasePrefix() {
        return "pkmagic";
    }

    @Override
    public String packageName() {
        return "me.domirusz24.pkmagicspells";
    }

    @Override
    public DataBaseTable[] getTables() {
        return new DataBaseTable[0];
    }

    @Override
    protected PAPIManager papiManager() {
        return null;
    }

    @Override
    protected void loadConfigs() {
        util = super.util;
        configM = super.configM;
        magicSpellsConfig = new MagicSpellsConfig("MagicSpells.yml", this);
    }

    @Override
    public void sqlLoad() {

    }

    @Override
    protected void _loadManagers() {
        Bukkit.getScheduler().runTask(this, () -> {
            magicSpellsM = new MagicSpellsManager(this, MagicSpells.getInstance());
            magicSpellsM.load();
        });
    }

    @Override
    protected void _loadCommands() {
        commandM = super.commandM;
    }

    public static PKSpellsListener pkListener;

    @Override
    protected void _registerEvents() {
        pkListener = new PKSpellsListener();
        Bukkit.getPluginManager().registerEvents(pkListener, this);
    }

    @Override
    protected void _disable() {
        HandlerList.unregisterAll(pkListener);
    }

    @Override
    protected void _shutOffPlugin() {
        HandlerList.unregisterAll(pkListener);
    }

    @Override
    public PlayerData registerPlayer(String s, UUID uuid) {
        return null;
    }
}
