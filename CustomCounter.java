package com.obnoxiousNinja;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
 
public class CustomCounter {
    private int mute_warning = 0, kick_warning = 0, warning_max = 3, mute, kick;
    private String pluginName = CapsCheck.getPluginName();

    public void addMuteWarning() {
        setMute_warning(getMute_warning() + 1);
    }
 
 
    public int addKickWarning(Player name) {
    	Player player = name;
        if(kick_warning >= warning_max){
        	player.kickPlayer("You have been kicked for excessive caps!");
        	this.mute_warning = getKick_warning();
        	return mute_warning;
        } else {
        	 String kick_result = (warning_max - getKick_warning()) != 1 ? " warnings left" : " warning left";
        	player.sendMessage(pluginName + ChatColor.RED + "Excessive caps is not allowed!\nYou have " + (warning_max - getKick_warning()) + kick_result + ".");
        	return getKick_warning() + 1;
        }
    }
 
    public void resetMute(){
        setMute(0);
    }
 
    public void resetKick(){
        setKick(0);
    }
 
    public int getMute_warning() {
        return mute_warning;
    }
 
    public void setMute_warning(int mute_warning) {
        this.mute_warning = mute_warning;
    }
 
 
    public int getKick_warning() {
        return kick_warning;
    }
 
 
    public void setKick_warning(int kick_warning) {
        this.kick_warning = kick_warning;
    }
 
 
    public int getMute() {
        return mute;
    }
 
 
    public void setMute(int mute) {
        this.mute = mute;
    }
 
 
    public int getKick() {
        return kick;
    }
 
 
    public void setKick(int kick) {
        this.kick = kick;
    }
 
}
