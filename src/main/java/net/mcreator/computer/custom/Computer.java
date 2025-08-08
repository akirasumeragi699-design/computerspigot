package net.mcreator.computer.custom;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;

import java.net.URI;

public class Computer extends JavaPlugin implements Listener {

    private static final String RESOURCE_PACK_URL = "https://github.com/akirasumeragi699-design/computer-spigot-packdonttouch/raw/refs/heads/main/computer_pack.zip";
    private static final String RESOURCE_PACK_HASH = "4368831f66c7da3c7d85f7ac027fdda4ce51f0af";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Computer resource pack plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Computer resource pack plugin disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            player.setResourcePack(new URI(RESOURCE_PACK_URL).toString(), hexStringToByteArray(RESOURCE_PACK_HASH));
            getLogger().info("Sent resource pack to " + player.getName());
        } catch (Exception e) {
            getLogger().severe("Failed to send resource pack to " + player.getName() + ": " + e.getMessage());
        }
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
