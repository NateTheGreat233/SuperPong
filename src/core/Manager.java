package core;

public class Manager 
{

	private boolean shouldRun;
	
	public void startGame()
	{
		shouldRun = true;
		runGame();
	}
	
	private void runGame()
	{
		while (shouldRun)
		{
			
		}
		endGame();
	}
	
	private void endGame()
	{
		System.exit(0);
	}
	
	public void setShouldRun(boolean b)
	{
		shouldRun = b;
	}
	
}
