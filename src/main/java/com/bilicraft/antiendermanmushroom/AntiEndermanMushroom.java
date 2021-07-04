package com.bilicraft.antiendermanmushroom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiEndermanMushroom extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEndermanPutMushroom(EntityChangeBlockEvent event){
        if(!(event.getEntity() instanceof Enderman)){
            return;
        }
        if(event.getEntity().getWorld().getEnvironment() == World.Environment.NORMAL){
            return;
        }
        if(isMushroom(event.getTo()) || isMushroom(event.getBlockData().getMaterial())){
            event.setCancelled(true);
            Enderman enderman = (Enderman) event.getEntity();
            enderman.setCarriedBlock(null);
        }
    }

    private boolean isMushroom(Material type){
        return type == Material.BROWN_MUSHROOM
                || type == Material.RED_MUSHROOM
                || type == Material.CRIMSON_FUNGUS
                || type == Material.WARPED_FUNGUS;
    }
}
