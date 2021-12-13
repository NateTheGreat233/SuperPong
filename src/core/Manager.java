package core;

import display.Window;

public class Manager 
{

	private static Manager manager;
	private boolean shouldRun;
	private Window window;
	private int windowWidth;
	private int windowHeight;
	
	private long lastTime;
	private int targetFPS;
	private long optimalTime;
	
	public Manager()
	{
		if (manager == null)
		{
			windowWidth = Window.SCREEN_WIDTH*3/4;
			windowHeight = Window.SCREEN_HEIGHT*3/4;
			targetFPS = 60;
			optimalTime = 1000000000/targetFPS;
			lastTime = 0;
			manager = this;
		}
	}
	
	public void startGame()
	{
		shouldRun = true;
		window = new Window(windowWidth, windowHeight);
		runGame();
	}
	
	private void runGame()
	{
		while (shouldRun)
		{
			if (lastTime == 0)
			{
				lastTime = System.nanoTime();
			}
			long currentTime = System.nanoTime();
			long difference = (currentTime - lastTime);
			lastTime = currentTime;
			double delta = (double)difference/(double)optimalTime;
			
			update(delta);
			render();
		}
		endGame();
	}
	
	private void endGame()
	{
		System.exit(0);
	}
	
	private void update(double delta)
	{
		window.getCurrentPanel().updateObjects(delta);
	}
	
	private void render()
	{
		window.getCurrentPanel().repaint();
		window.getCurrentPanel().revalidate();
	}
	
	public void setShouldRun(boolean b)
	{
		shouldRun = b;
	}
	
	public Window getWindow()
	{
		return window;
	}
	
	public int getWindowWidth()
	{
		return windowWidth;
	}
	
	public int getWindowHeight()
	{
		return windowHeight;
	}
	
	public static Manager getManager()
	{
		return manager;
	}
	
}
