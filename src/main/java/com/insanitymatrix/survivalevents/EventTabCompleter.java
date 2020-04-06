/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insanitymatrix.survivalevents;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

/**
 *
 * @author dvpie
 */
public class EventTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("survivalevent")) {
            List<String> list = new ArrayList<>();
            if(args.length == 0) {
               list.add("item");
               list.add("mining");
               list.add("ocean");
               return list;
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("item")) {
                    Material[] m = Material.values();
                    for(Material material : m) {
                        list.add(material.toString());
                    }
                    return list;
                }else if(args[0].equalsIgnoreCase("mining") || args[0].equalsIgnoreCase("ocean")) {
                    list.add("3600");
                    list.add("7200");
                    list.add("10800");
                    return list;
                }
            }
        }
        return null;
    }
}
