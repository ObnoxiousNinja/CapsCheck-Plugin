package com.obnoxiousNinja;

import java.util.Calendar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerHandler implements Listener{
	private int warning_max = 3;
	private static int unmute_time;
	private String pluginName = CapsCheck.getPluginName();
	public static String chat;
	int MaxCaps = CapsCheck.getMaxCaps();
	
	public PlayerHandler (CapsCheck plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void playerJoinMessage(PlayerJoinEvent event){
		Player player = event.getPlayer();
		CapsCheck.cmap.put(player.getName(), new CustomCounter());
		boolean muted = CapsCheck.is_muted.get(player.getName());
		
		if(muted == false){
			if(!(player.isOp())){
				player.sendMessage(pluginName + ChatColor.WHITE + "You have "+ warning_max +" warnings left.");
			}
		} else {
			player.sendMessage(pluginName + ChatColor.WHITE + "You have been muted for excessive caps!");
		}
	}
	
	@EventHandler
    public String onSpeak(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		CustomCounter cc = CapsCheck.cmap.get(player.getName());
		boolean muted = CapsCheck.is_muted.get(player.getName());
		
		String chat = event.getMessage(); //Gets the attempted message
        String[] message = chat.split("(?:X)[A-Z]{"+ MaxCaps +"}|[.!?_]|[\\s]");
        
        if(cc.getMute_warning() >= warning_max){
			unmute_time = Calendar.getInstance().get(Calendar.MINUTE) + 05;
			muted = true;
			Bukkit.broadcastMessage(pluginName + ChatColor.GREEN + (player.getDisplayName () + " has been "+ ChatColor.RED + "muted" + ChatColor.GREEN +" for excessive caps!"));
			cc.resetMute();
		}
        
        if(getCapsSize(message) > MaxCaps && !(player.isOp())){
    		if(muted == true && (Calendar.getInstance().get(Calendar.MINUTE) < unmute_time)){
    			event.setCancelled(true);
    			player.sendMessage(pluginName + ChatColor.RED + "You may not speak in chat!  You have muted for 5 minutes.");
    		} else {
    			CapsCheck.is_muted.put(player.getName(), new Boolean(false));
    		}
        	
        	String kick_result = (warning_max - cc.getKick_warning()) != 1 ? " warnings left" : " warning left";
			
            event.getPlayer().sendMessage(pluginName + ChatColor.RED + "Excessive caps is not allowed!\nYou have " + (warning_max - cc.getKick_warning()) + kick_result + ".");
            event.setMessage(chat.toLowerCase());
        	cc.addKickWarning(player);
        }
        return chat;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		CapsCheck.cmap.remove(player.getName());
		boolean is_muted = CapsCheck.is_muted.get(player.getName());
		
		if(is_muted == false){
			CapsCheck.is_muted.remove(player.getName());
		}
	}
	
	/*@EventHandler
	public void playerTag(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.contains("@")){
			if(player.isOnline()){
				
			}
		}
	}*/

	private int getCapsSize(String[] args) {
        int i = 0;
        for(String string: args){
            if(string.equals(new String(string.toUpperCase()))){
                i++;
            }
        }
        return i;
    }
}
