package me.domirusz24.pkmagicspells.activations;

import me.domirusz24.pkmagicspells.model.SpellBender;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbilityActivation implements Listener {

    // -------------

    public static List<Block> getNearbyBlocks(Location location, int radius, Material... materials) {
        List<Block> list = new ArrayList<>();
        for(int x = (radius * -1); x <= radius; x++) {
            for(int y = (radius * -1); y <= radius; y++) {
                for(int z = (radius * -1); z <= radius; z++) {
                    Block b = location.getWorld().getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
                    for (Material material : materials) {
                        if (b.getType() == material) {
                            list.add(b);
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    // -------------


    protected SpellBender player;

    public AbilityActivation(SpellBender player){
        this.player = player;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    private boolean fulfilled = false;

    public boolean setFulfill(boolean value) {
        fulfilled = value;
        if (fulfilled) {
            return player.tryCasting();
        }
        return false;
    }

    public void unregister() {
        fulfilled = false;
    }
}
