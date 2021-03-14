package com.fkopp.tictactoe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fkopp.tictactoe.TicTacToe;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 299;
		config.height = 299;
		config.resizable = false;
		
		new LwjglApplication(new TicTacToe(), config);
	}
}
