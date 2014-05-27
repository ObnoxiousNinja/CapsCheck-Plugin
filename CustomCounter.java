package com.obnoxiousNinja;

public class CustomCounter {
	private int mute_warning, kick_warning, mute, kick;
	
	
	public void addMuteWarning() {
		setMute_warning(getMute_warning() + 1);
	}

	public void addKickWarning() {
		setKick_warning(getKick_warning() + 1);
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
