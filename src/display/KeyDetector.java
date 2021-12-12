package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Manager;

public class KeyDetector implements KeyListener 
{
	
	private Manager manager;
	
	public KeyDetector()
	{
		manager = Manager.getManager();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			manager.setShouldRun(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{

		
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

		
		
	}

}
