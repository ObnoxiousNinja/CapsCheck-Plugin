package com.obnoxiousNinja;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CapsCheck extends JavaPlugin{
	public static int MaxCaps = 2;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static String pluginName = ChatColor.GOLD + "[CapsCheck]: ";
	public int counter;
	
	public static Map<String, CustomCounter> cmap; //For warnings
	public static Map<String, Boolean> is_muted; //For when players are actually muted
	
	public static String getPluginName(){
		return pluginName;
	}
	
	@Override
	public void onEnable(){
		new PlayerHandler(this);
		CapsCheck.cmap = new HashMap<String, CustomCounter>();
		CapsCheck.is_muted = new HashMap<String, Boolean>();
		
		logger.info("[CapsCheck]: CapsCheck has been enabled!");
	}
	
	@Override
	public void onDisable(){
		logger.info("[CapsCheck]: CapsCheck has been disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
		Player player = (Player) sender;
		
		if(cmdLabel.equalsIgnoreCase("ccmax")){
			if(player.hasPermission("CapsCheck.max") && args.length == 1){
				String maxcaps = args[0];
				MaxCaps = Integer.parseInt(maxcaps);
				
				player.sendMessage(pluginName + ChatColor.WHITE + " The maximum amount of capital letters allowed is now " + MaxCaps);
			} else if(player.hasPermission("CapsCheck.max") && args.length != 1){
				player.sendMessage(pluginName + ChatColor.RED + "ERROR: Syntax is /cc max [integer]!");
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}

			return true;
		}	
		return false;
	}
	
	public static int getMaxCaps(){
		return MaxCaps;
	}
}
