package com.ax4987.levelprotect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Map;

public class EventHandler implements Listener {
    @org.bukkit.event.EventHandler
    public static void onLevelChange(PlayerLevelChangeEvent event){
        for (Map<String, Map<Integer,Integer>> map : LevelProtect.information){
            for (String s : map.keySet()){
                if (s.equals(event.getPlayer().getWorld().getName())){
                    if (event.getNewLevel() == map.get(s).get(1)){
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',LevelProtect.Reach_Level));
                    }
                }
            }
        }
    }
    @org.bukkit.event.EventHandler
    public static void onPlayerDamage(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player){
            if (event.getDamager() instanceof Player) {
                if (isIn((Player) event.getEntity())) {
                    event.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&',LevelProtect.Player_pvp));
                    event.setCancelled(true);
                }else if (isIn((Player) event.getDamager())){
                    event.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&',LevelProtect.None_pvp));
                    event.setCancelled(true);
                }
            }

        }
    }
    private static Boolean isIn(Player player){
        for (Map<String, Map<Integer,Integer>> map : LevelProtect.information){
            for (String s : map.keySet()){
                if (s.equals(player.getWorld().getName())){
                    if (map.get(s).get(2) <= player.getLevel() && player.getLevel() < map.get(s).get(1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
