package org.cstutorials;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

//import org.newdawn.slick.Graphics;

public class Game
{

	public static final int DISPLAY_WIDTH = 640;
	public static final int DISPLAY_HEIGHT = 480;
	private long lastFrame;
	private int fps;
	private long lastFPS;

	public static GameObjectManager gameObjectManager;
	private static Graphics g;
	public static Ship ship;

	public Game()
	{
		initGL();
		init();
		getDelta();
		lastFPS = getTime();
		gameLoop();
	}

	public void init()
	{
		gameObjectManager = new GameObjectManager();
		g = new Graphics();
		ship = new Ship();
	}

	private void gameLoop()
	{
		while (!Display.isCloseRequested())
		{
			displayInput();
			int delta = getDelta();
			updateFPS();

			preRender();

			render(delta);

			Display.update();
		}
		tearDown();
	}

	private void preRender()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}

	private void displayInput()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			tearDown();
		}
	}

	private void render(int delta)
	{
		gameObjectManager.update(delta);
		gameObjectManager.render(g);
	}

	private int getDelta()
	{
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	private long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private void updateFPS()
	{
		if (getTime() - lastFPS > 1000)
		{
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL()
	{

		try
		{
			Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
			Display.setTitle("Asteroids");
			Display.setVSyncEnabled(true);
			Display.create();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	private void tearDown()
	{
		gameObjectManager.deleteBuffers();
		Display.destroy();
		System.exit(0);
	}

	public static void main(String[] args)
	{
		new Game();
	}
}