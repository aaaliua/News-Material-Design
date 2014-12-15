package com.aaaliua.load.library;

import android.R.integer;
import android.annotation.SuppressLint;

public enum LoaderType {

	SNAKE_3D("snake_3d_8"), 
	FLIP_FLOP("flip_flop_32"), 
	INTERSECT("intersect_20"), 
	MINI_BALLS("mini_balls_12"), 
	PACMAN("pacman_10"), 
	PULSE("pulse_8"), 
	RADAR("radar_16"), 
	RING_RACE("ring_race_18"), 
	RUNNING_SNAKE("running_snake_24"),
	SEARCHING("searching_18"), 
	SKYPE("skype_29"), 
	SPIN_AND_FADE("spin_fade_18"), 
	SPINNING_GEAR("spinning_gear_10"), 
	TIME_MACHINE("time_machine_8"),
	WINDOWS_8("windows_8_loader_75"),
	MAP_MARKER("map_marker_15");

	private String spriteName;
	private int frames;
	
	private LoaderType(String nameIn) {
		this.spriteName = nameIn;
		
		int lastIndex = spriteName.lastIndexOf("_");
		String sub = spriteName.substring(lastIndex + 1,spriteName.length());
		frames = Integer.valueOf(sub);
	}
	
	public String getSpriteName(){
		return spriteName;
	}
	public int getFrames(){
		return frames;
	}
	
	public static int getCount(){
		return LoaderType.values().length;
	}

	public static LoaderType getLoaderByName(String name){
		LoaderType load = null;
		
		for(int i = 0;i<LoaderType.values().length;i++){
			System.out.println(name  + "-------->" +values()[i].toString().toLowerCase().replaceAll("_", ""));
			if(name.equals(values()[i].toString().toLowerCase().replaceAll("_", ""))){
				
				load = values()[i];
				break;
			}
		}
		
		return load;
	}
	
}
