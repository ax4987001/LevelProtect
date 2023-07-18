package com.ax4987.levelprotect;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LevelProtect extends JavaPlugin {
    public static FileConfiguration config;
    public static List<String> sitting;
    public static List<Map<String,Map<Integer,Integer>>> information;
    public static String Reach_Level;
    public static String None_pvp;
    public static String Player_pvp;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config = getConfig();
        sitting = config.getStringList("Sitting");
        information = regular(sitting);
        Reach_Level = config.getString("Messages.Reach-Level");
        None_pvp = config.getString("Messages.None-pvp");
        Player_pvp = config.getString("Messages.Player-pvp");
        getServer().getPluginManager().registerEvents(new EventHandler(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private List<Map<String,Map<Integer,Integer>>> regular(List<String> sitting){
        String[] parts;
        Map<Integer,Integer> level = new HashMap<>();
        Map<String,Map<Integer,Integer>> value = new HashMap<>();
        List<Map<String,Map<Integer,Integer>>> regular = new ArrayList<>();
        for (String s : sitting){
            parts = s.split("\\|",3);
            level.clear();
            value.clear();
            level.put(1,Integer.parseInt(parts[1]));
            level.put(2,Integer.parseInt(parts[2]));
            value.put(parts[0],level);
            regular.add(value);
        }
        return regular;
    }
}
