package com.mc1510ty.spigot;

import com.mc1510ty.common.GroupAddManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Spigot/Paper 版が有効化されました。");
        // Join イベント登録
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // 初参加のみ追加したい場合:
        if (!player.hasPlayedBefore()) {
            GroupAddManager.addPlayerGroup(player.getUniqueId());
        }
    }
}
