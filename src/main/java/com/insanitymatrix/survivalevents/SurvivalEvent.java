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
    private PotionEffect[] potionEffects;
    private ItemStack item;
    private long Start;
    private long lastBroadcast;
    private int Duration;
    
    public SurvivalEvent(String type, PotionEffect potionEffect, long start, int duration) {
        this.type = type;
        this.potionEffect = potionEffect;
        this.Start = start;
        this.lastBroadcast = start;
        this.Duration = duration;
    }
    public SurvivalEvent(String type, PotionEffect[] potionEffects, long start, int duration) {
        this.type = type;
        this.potionEffects = potionEffects;
        this.Start = start;
        this.lastBroadcast = start;
        this.Duration = duration;
    }
    public SurvivalEvent(String type, ItemStack item, long start, int duration) {
        this.type = type;
        this.item = item;
        this.Start = start;
        this.Duration = duration;
    }

    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public void setPotionEffects(PotionEffect[] potionEffects) {
        this.potionEffects = potionEffects;
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

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public long getLastBroadcast() {
        return lastBroadcast;
    }

    public void setLastBroadcast(long lastBroadcast) {
        this.lastBroadcast = lastBroadcast;
    }
    
}
