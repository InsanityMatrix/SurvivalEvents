/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insanitymatrix.survivalevents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

/**
 *
 * @author dvpie
 */
public class SurvivalEvents extends JavaPlugin {
    //Set default Event Duration to 1.5 Hours
    public SurvivalEvents plugin;
    private final int DEFAULT_EVENT_DURATION = 5400;
    private Map<String, SurvivalEvent> ActiveEvents = new HashMap<>();
    private ArrayList<Player> playersOnline = new ArrayList<>(); 
    @Override
    public void onEnable() {
        updatePlayersList(Bukkit.getServer().getOnlinePlayers());
        plugin = this;
        getCommand("survivalevent").setTabCompleter(new EventTabCompleter());
        BukkitScheduler scheduler = getServer().getScheduler();
        
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
             refreshEvents();   
            }
        }, 0L, (long)(20 * 5));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("survivalevent")) {
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED.toString() + "Not enough arguments to start an event.");
            } else {
                startEvent(sender,cmd,label,args);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void refreshEvents() {
        updatePlayersList(Bukkit.getServer().getOnlinePlayers());
        Set<String> eventKeys = ActiveEvents.keySet();
        long currentTime = System.currentTimeMillis();
        for(String key : eventKeys) {
            SurvivalEvent event = ActiveEvents.get(key);
            if(currentTime - event.getStart() < event.getDuration() * 1000) {
             //Event is still going on
             if(event.getType().equals("Potion")) {
                 PotionEffect effect = event.getPotionEffect();
                 for(Player player : playersOnline) {
                     player.addPotionEffect(effect);
                 }
             }
            } else {
                ActiveEvents.remove(key);
            }
        }
    }
    public void updatePlayersList(Collection<?> onlinePlayers) {
        for(Object playerObject : onlinePlayers) {
            if(playerObject instanceof Player) {
                Player player = (Player)playerObject;
                if(!playersOnline.contains(player)) {
                    playersOnline.add(player);
                }
            }
        }
        for(Player player : playersOnline) {
            if(!player.isOnline()) {
                playersOnline.remove(player);
            }
        }
    }
    public void startEvent(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1) {
            String eventType = args[0];
            if(eventType.equalsIgnoreCase("Mining")) {
                addMiningEvent(DEFAULT_EVENT_DURATION);
            } else if(eventType.equalsIgnoreCase("item")) {
                sender.sendMessage(ChatColor.RED.toString() + "Not enough arguments for item event");
            }
        } else if (args.length == 2) {
            if(args[0].equalsIgnoreCase("item")) {
                Material item = Material.getMaterial(args[1]);
                ItemStack stack = new ItemStack(item, 1);
                addItemEvent(stack, DEFAULT_EVENT_DURATION);
            }else {
                String duration = args[1];
                if(duration.equalsIgnoreCase("Stop")) {
                   String eventKey = args[0];
                   if(eventKey.equalsIgnoreCase("Mining"))
                       removeEvent("Mining");
                } else {
                    int dur = Integer.parseInt(duration);
                    String event = args[0];
                    if(event.equalsIgnoreCase("Mining")) {
                        addMiningEvent(dur);
                    }
                }
            }
        } else if(args.length == 3) {
            if(args[0].equalsIgnoreCase("item")) {
                Material item = Material.getMaterial(args[1]);
                int amount = Integer.parseInt(args[2]);
                ItemStack stack = new ItemStack(item, amount);
                addItemEvent(stack, DEFAULT_EVENT_DURATION);
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "This is not an event type");
            }
        } else if(args.length == 4) {
            if(args[0].equalsIgnoreCase("item")) {
                Material item = Material.getMaterial(args[1]);
                int amount = Integer.parseInt(args[2]);
                ItemStack stack = new ItemStack(item, amount);
                int duration = Integer.parseInt(args[3]);
                addItemEvent(stack, duration);
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "This is not an event type");
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Too many arguments!");
        }
    }
    public void removeEvent(String key) {
        ActiveEvents.remove(key);
    }
    public void addMiningEvent(int duration) {
        PotionEffect mining = new PotionEffect(PotionEffectType.FAST_DIGGING,8,2);
        SurvivalEvent newEvent = new SurvivalEvent("Potion",mining,System.currentTimeMillis(),duration);
        ActiveEvents.put("Mining", newEvent);
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN.toString() + "[Survival Events] " + ChatColor.YELLOW.toString() + "A mining event has started!");
        refreshEvents();
    }
    public void addItemEvent(ItemStack item, int duration) {
        SurvivalEvent newEvent = new SurvivalEvent("Item",item,System.currentTimeMillis(),duration);
        ActiveEvents.put("Item", newEvent);
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN.toString() + "[Survival Events] " + ChatColor.YELLOW.toString() + "An item event has started!");
        refreshEvents();
    }

}
