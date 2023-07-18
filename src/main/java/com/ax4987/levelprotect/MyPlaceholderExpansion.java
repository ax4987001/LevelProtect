package com.ax4987.levelprotect;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class MyPlaceholderExpansion extends PlaceholderExpansion {
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public String getIdentifier() {
        return "levelprotect";
    }
    @Override
    public String getAuthor() {
        return "ax4987";
    }
    @Override
    public String getVersion() {
        return "1.0";
    }
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("world_level")){
            for (Map<String, Map<Integer,Integer>> map : LevelProtect.information){
                for (String s : map.keySet()){
                    if (s.equals(player.getWorld().getName())){
                        return String.valueOf(map.get(s).get(1));
                    }
                }
            }
        }
        return null;
    }
}