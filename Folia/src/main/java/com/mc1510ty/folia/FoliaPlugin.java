package com.mc1510ty.folia;

import com.mc1510ty.common.GroupAddManager;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FoliaPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Folia 版が有効化されました。");
        // Join イベント登録
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // 初参加のみ追加
        if (!player.hasPlayedBefore()) {
            GlobalRegionScheduler scheduler = Bukkit.getGlobalRegionScheduler();
            // Runnable で渡すだけで OK
            scheduler.execute(this, () -> {
                GroupAddManager.addPlayerGroup(player.getUniqueId());
            });
        }
    }
}
