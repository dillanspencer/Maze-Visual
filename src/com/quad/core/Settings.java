package com.quad.core;

import java.awt.Dimension;

/**
 * 
 * @author Dillan Spencer
 * This class holds static variables that 
 * can be accessed from any class
 *
 */

public class Settings {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	public static final int SCALE = 2;	
	
	public static final int FPS = 60;
	
	public static boolean LIGHT = false;
		
	public static void setLight(GameContainer gc, boolean i){
		Settings.LIGHT = i;
		gc.setLightEnable(i);
		gc.setDynamicLights(i);
	}
	
	public static void changeFps(GameContainer gc, int i){
		gc.setFrameCap(i);
	}
	

}
