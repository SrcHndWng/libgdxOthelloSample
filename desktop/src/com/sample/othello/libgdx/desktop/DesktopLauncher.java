package com.sample.othello.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sample.othello.libgdx.Application;
import com.sample.othello.libgdx.gameLogic.Const;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Const.Desktop.width;
		config.height = Const.Desktop.height;
		new LwjglApplication(new Application(), config);
	}
}
