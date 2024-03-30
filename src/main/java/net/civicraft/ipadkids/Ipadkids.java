package net.civicraft.ipadkids;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Ipadkids extends JavaPlugin implements @NotNull Listener {

    @Override
    public void onEnable() {
        try {
            getServer().getPluginManager().registerEvents(this, this);
        } catch (Exception e) {
            getLogger().severe("Error! " + e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage().toLowerCase();
        if (message.equals("skibidi bop mm dada")) {
            Location location = event.getPlayer().getLocation();
            spawnChargedCreeper(location);
            event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "KILL YOURSELF");
        }
    }

    private void spawnChargedCreeper(Location location) {
        Bukkit.getScheduler().runTask(this, () -> {
            getLogger().info("Spawning charged creeper at location: " + location);
            World world = location.getWorld();
            if (world != null) {
                Creeper creeper = world.spawn(location, Creeper.class);
                creeper.setPowered(true);
                creeper.setCustomName(ChatColor.BOLD + "" + ChatColor.BLUE + "Ipad Kid Punisher");
                getLogger().info("Charged creeper spawned successfully");
            } else {
                getLogger().warning("World is null, unable to spawn charged creeper");
            }
        });
    }
}