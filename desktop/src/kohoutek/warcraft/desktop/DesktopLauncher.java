package kohoutek.warcraft.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import kohoutek.warcraft.Warcraft;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		// TODO: config from command line args
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS 	= -1;
		config.foregroundFPS	= 30;
		config.width			= 800;
		config.height			= 600;
		config.resizable		= true;
		config.useGL30			= false;
		new LwjglApplication(new Warcraft(), config);
	}
}
