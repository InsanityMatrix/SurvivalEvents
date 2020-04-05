/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insanitymatrix.survivalevents;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 *
 * @author dvpie
 */
public class SurvivalEvent {
    private String type;
    private PotionEffect potionEffect;
    private ItemStack item;
    private long Start;
    private int Duration;
    
    public SurvivalEvent(String type, PotionEffect potionEffect, long start, int duration) {
        this.type = type;
        this.potionEffect = potionEffect;
        this.Start = start;
        this.Duration = duration;
    }
    public SurvivalEvent(String type, ItemStack item, long start, int duration) {
        this.type = type;
        this.item = item;
        this.Start = start;
        this.Duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    public long getStart() {
        return Start;
    }

    public void setStart(long Start) {
        this.Start = Start;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }
    
}
